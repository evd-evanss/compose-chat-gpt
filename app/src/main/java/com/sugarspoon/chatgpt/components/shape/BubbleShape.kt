package com.sugarspoon.chatgpt.components.shape

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density

/**
 * BubbleShape
 *
 * @author Evandro Costa
 *
 */
class BubbleShape(
    private val cornerRadius: Float,
    private val isResponse: Boolean
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: Density
    ): Outline {

        return Outline.Rounded(
            RoundRect(
                rect = size.toRect(),
                topLeft = CornerRadius(cornerRadius, cornerRadius),
                topRight = cornerBubble(isResponse),
                bottomRight = CornerRadius(cornerRadius, cornerRadius),
                bottomLeft = cornerBubble(!isResponse)
            )
        )
    }

    val cornerBubble: (Boolean) -> CornerRadius = {
        if (it) {
            CornerRadius(8f, 8f)
        } else {
            CornerRadius(cornerRadius, cornerRadius)
        }
    }
}