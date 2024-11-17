package com.example.surveyapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = DeepPurple,             // Rich purple for primary elements
    primaryVariant = Orchid,          // Vibrant orchid for accentuating details
    secondary = AquaBlue,             // Refreshing blue-green for interactive elements
    background = Color.Black,         // A dark background for night mode
    surface = DeepPurple,             // Primary surface with dark contrast
    onPrimary = Color.White,          // Ensures text and icons are readable on primary
    onSecondary = Color.White,        // White text for secondary elements
    onBackground = Color.White,       // High contrast for text
    onSurface = Color.White           // Readable text on dark surfaces
)

private val LightColorPalette = lightColors(
    primary = AquaBlue,               // Modern blue-green for primary elements
    primaryVariant = DeepPurple,      // Elegant purple for accents
    secondary = CoralPink,            // Warm coral for interactive elements
    background = LavenderBlush,       // Soft lavender for a gentle background
    surface = Color.White,            // Clean white surfaces
    onPrimary = Color.White,          // Text on primary remains white
    onSecondary = Color.Black,        // Black text for secondary elements
    onBackground = Color.Black,       // Text is clearly visible
    onSurface = Color.Black           // High contrast text on light surfaces
)

@Composable
fun SurveyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
