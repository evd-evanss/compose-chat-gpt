package com.sugarspoon.chatgpt.anim

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with

object ScrollAnimation {
    @OptIn(ExperimentalAnimationApi::class)
    operator fun invoke(): ContentTransform {
        return slideInVertically(
            initialOffsetY = { 50 },
            animationSpec = tween()
        ) + fadeIn() with slideOutVertically(
            targetOffsetY = { -50 },
            animationSpec = tween()
        ) + fadeOut()
    }
}