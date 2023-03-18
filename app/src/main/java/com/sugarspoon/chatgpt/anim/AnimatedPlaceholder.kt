package com.sugarspoon.chatgpt.anim

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import com.sugarspoon.chatgpt.theme.Dimens
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedPlaceholder(
    hints: List<String>,
    textStyle: FontFamily = MaterialTheme.typography.h1.fontFamily!!,
    textColor: Color = MaterialTheme.colors.onSurface,
    content: @Composable (String) -> Unit
) {
    val iterator = hints.listIterator()

    val target by produceState(initialValue = hints.first()) {
        iterator.doWhenHasNextOrPrevious {
            value = it
        }
    }

    AnimatedContent(
        targetState = target,
        transitionSpec = { ScrollAnimation() }
    ) { str ->
        content(str)
    }
}

suspend fun <T> ListIterator<T>.doWhenHasNextOrPrevious(
    delayMills: Long = 1500,
    doWork: suspend (T) -> Unit
) {
    while (hasNext() || hasPrevious()) {
        while (hasNext()) {
            delay(delayMills)
            doWork(next())
        }
        while (hasPrevious()) {
            delay(delayMills)
            doWork(previous())
        }
    }
}