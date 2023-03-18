package com.sugarspoon.chatgpt.components.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sugarspoon.chatgpt.theme.Typography

@Composable
fun Body2(
    text: String,
    modifier: Modifier
) {
    Text(
        text = text,
        style = Typography.body2,
        modifier = modifier
    )
}