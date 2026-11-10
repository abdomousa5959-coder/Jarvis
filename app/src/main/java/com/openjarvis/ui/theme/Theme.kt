package com.openjarvis.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object VoidColor {
    val Void950 = Color(0xFF080910)
    val Void900 = Color(0xFF10111B)
    val Void800 = Color(0xFF191B29)
    val Violet = Color(0xFF9B7BFF)
    val Cyan = Color(0xFF4DD0E1)
    val Green = Color(0xFF43D19E)
    val Red = Color(0xFFFF6B78)
    val Amber = Color(0xFFFFC857)
    val TextPrimary = Color(0xFFF4F2FF)
    val TextSecondary = Color(0xFFB8B5C8)
    val TextDisabled = Color(0xFF777487)
    val BorderSubtle = Color(0xFF303144)
    val BorderGlow = Color(0xFF6551A6)
}

@Composable
fun OpenJarvisTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = VoidColor.Violet,
            secondary = VoidColor.Cyan,
            background = VoidColor.Void950,
            surface = VoidColor.Void900,
            onPrimary = Color.White,
            onSecondary = Color.Black,
            onBackground = VoidColor.TextPrimary,
            onSurface = VoidColor.TextPrimary
        ),
        content = content
    )
}
