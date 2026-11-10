package com.openjarvis.ui.tutorial

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import com.openjarvis.accessibility.JarvisAccessibilityService
import com.openjarvis.agent.Action
import com.openjarvis.voice.VoiceManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TutorialMode(private val context: Context) {
    private val voiceManager = VoiceManager(context)
    private val modeFlow = MutableStateFlow<TutorialState>(TutorialState.Off)
    private val currentStepFlow = MutableStateFlow<TutorialStep?>(null)
    private var overlayWindow: WindowManager? = null
    private var highlightView: TutorialHighlightView? = null
    private val stepHistory = mutableListOf<String>()

    sealed class TutorialState {
        object Off : TutorialState()
        object Guided : TutorialState()
        object Observe : TutorialState()
        object Shadow : TutorialState()
    }

    data class TutorialStep(
        val stepNumber: Int,
        val description: String,
        val targetBounds: RectF?,
        val narration: String
    )

    val state: StateFlow<TutorialState> = modeFlow
    val currentStep: StateFlow<TutorialStep?> = currentStepFlow

    suspend fun startGuidedMode() {
        modeFlow.value = TutorialState.Guided
    }

    suspend fun executeStep(action: Action, bounds: RectF?) {
        if (modeFlow.value != TutorialState.Guided) return
        val stepNumber = stepHistory.size + 1
        val narration = when (action.action) {
            Action.OPEN_APP -> "Opening ${action.label ?: action.packageName}"
            Action.TAP -> "Tapping ${action.text ?: "the button"}"
            Action.TYPE -> "Typing ${action.value.orEmpty()}"
            Action.SCROLL -> "Scrolling ${action.direction ?: "down"}"
            Action.PRESS_BACK -> "Pressing back"
            Action.PRESS_HOME -> "Going to home screen"
            else -> "Performing ${action.action}"
        }
        currentStepFlow.value = TutorialStep(stepNumber, action.action, bounds, narration)
        showHighlight(bounds)
        voiceManager.speak(narration)
        delay(1000)
        executeAction(action)
        stepHistory.add("$stepNumber. ${action.action}")
        currentStepFlow.value = null
        hideHighlight()
    }

    private fun showHighlight(bounds: RectF?) {
        if (bounds == null) return
        try {
            highlightView = TutorialHighlightView(context).apply { setTarget(bounds) }
            overlayWindow = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                android.graphics.PixelFormat.TRANSLUCENT
            )
            overlayWindow?.addView(highlightView, params)
        } catch (_: Exception) {
            highlightView = null
        }
    }

    private fun hideHighlight() {
        try {
            highlightView?.let { overlayWindow?.removeView(it) }
        } catch (_: Exception) { }
        highlightView = null
    }

    private fun executeAction(action: Action) {
        val service = JarvisAccessibilityService.instance
        when (action.action) {
            Action.OPEN_APP -> action.packageName?.let { service?.openAppByPackage(it) }
            Action.TAP -> action.text?.let { service?.tapByText(it) }
            Action.TYPE -> action.value?.let { service?.typeText(it) }
            Action.PRESS_BACK -> service?.pressBack()
            Action.PRESS_HOME -> service?.pressHome()
            else -> Unit
        }
    }

    fun getSummary(): String = stepHistory.joinToString("\n")
    fun endSession() {
        hideHighlight()
        modeFlow.value = TutorialState.Off
        currentStepFlow.value = null
    }
    fun isActive(): Boolean = modeFlow.value != TutorialState.Off

    private class TutorialHighlightView(context: Context) : View(context) {
        private var targetBounds: RectF? = null
        private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = 0xFF9B59B6.toInt()
            style = Paint.Style.STROKE
            strokeWidth = 8f
        }
        fun setTarget(bounds: RectF) { targetBounds = bounds; invalidate() }
        override fun onDraw(canvas: Canvas) {
            targetBounds?.let { canvas.drawRoundRect(it, 16f, 16f, paint) }
        }
    }
}
