package com.openjarvis.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    onSaveProvider: (String, String, String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var provider by remember { mutableStateOf("Groq") }
    var baseUrl by remember { mutableStateOf("") }
    var apiKey by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("llama-3.1-70b-versatile") }

    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Jarvis Settings")
        OutlinedTextField(provider, { provider = it }, label = { Text("Provider") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(baseUrl, { baseUrl = it }, label = { Text("Base URL (optional)") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(apiKey, { apiKey = it }, label = { Text("API key") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(model, { model = it }, label = { Text("Model") }, modifier = Modifier.fillMaxWidth())
        Button(onClick = { onSaveProvider(provider, baseUrl, apiKey, model); onNavigateBack() }, modifier = Modifier.fillMaxWidth()) {
            Text("Save settings")
        }
        Button(onClick = onNavigateBack, modifier = Modifier.fillMaxWidth()) {
            Text("Back")
        }
    }
}
