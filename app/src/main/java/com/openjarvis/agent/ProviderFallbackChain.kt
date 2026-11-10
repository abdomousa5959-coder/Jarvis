package com.openjarvis.agent

import android.content.Context
import com.openjarvis.llm.UniversalAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONException

class ProviderFallbackChain(private val context: Context) {
    
    private val cooldownMap = mutableMapOf<String, Long>()
    private val _notification = MutableStateFlow<String?>(null)
    
    private val prefs = context.getSharedPreferences("jarvis_prefs", Context.MODE_PRIVATE)
    
    private val fallbackOrder: List<String>
        get() = prefs.getString("fallback_order", "Groq,Google Gemini,OpenRouter,Anthropic Claude,OpenAI")
            ?.split(",") ?: emptyList()
    
    suspend fun completeWithFallback(system: String, user: String): Result<String> {
        val provider = UniversalAdapter(context).getActiveProvider()
        val providerName = provider.name
        if (isInCooldown(providerName)) {
            return Result.failure(Exception("$providerName is temporarily unavailable"))
        }
        val result = provider.complete(system, user)
        if (result.isFailure) {
            val message = result.exceptionOrNull()?.message.orEmpty()
            if (message.contains("429")) cooldownMap[providerName] = System.currentTimeMillis() + 60_000
            if (message.contains("401")) cooldownMap[providerName] = System.currentTimeMillis() + 3_600_000
            _notification.value = "Provider $providerName failed: $message"
        }
        return result
    }

    private fun isInCooldown(name: String): Boolean {
        val cooldownUntil = cooldownMap[name] ?: return false
        return System.currentTimeMillis() < cooldownUntil
    }
    
    fun getNotification() = _notification
}

object JarvisErrorTranslator {
    
    fun translate(error: Throwable, providerName: String?): String {
        return when {
            error.message?.contains("401") == true ->
                "API key rejected by " + (providerName ?: "provider") + ". Check your key in Settings."
            
            error.message?.contains("429") == true ->
                "Rate limited by " + (providerName ?: "provider") + ". Trying next provider..."
            
            error.message?.contains("503") == true ->
                "\${providerName ?: "Provider"} is down. Try a different provider in Settings."
            
            error.message?.contains("timeout") == true ->
                "Request timed out after 30s. Your internet may be slow."
            
            error.message?.contains("Unable to resolve host") == true ->
                "No internet connection. Switch to Local Model in Settings for offline use."
            
            error is JSONException ->
                "AI returned unexpected response. Retrying with stricter instructions..."
            
            error.message?.contains("SecurityException") == true ->
                "Permission denied. Check that Accessibility Service is still enabled."
            
            error.message?.contains("NullPointerException") == true ->
                "Screen changed unexpectedly. Trying to recover..."
            
            else -> "Something went wrong: " + (error.message?.take(100) ?: "Unknown error")
        }
    }
}