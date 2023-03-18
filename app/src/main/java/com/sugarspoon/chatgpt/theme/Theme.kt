package com.sugarspoon.chatgpt.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Pistachio,
    primaryVariant = Polar,
    secondary = HoneyDewAnalogous,
    secondaryVariant = FountainBlue,
    error = FeedbackError,
    background = BackgroundDark,
)

private val LightColorPalette = lightColors(
    primary = Pistachio,
    primaryVariant = Polar,
    secondary = HoneyDewAnalogous,
    secondaryVariant = FountainBlue,
    error = FeedbackError,
    background = BackgroundLight,
)

@Composable
fun OtpViewTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val systemUiController = rememberSystemUiController()
    if(darkTheme) {
        systemUiController.setStatusBarColor(
            color = DarkGrey
        )
    } else {
        systemUiController.setStatusBarColor(
            color = LightStatusBar
        )
    }
    systemUiController.isNavigationBarVisible = false

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}