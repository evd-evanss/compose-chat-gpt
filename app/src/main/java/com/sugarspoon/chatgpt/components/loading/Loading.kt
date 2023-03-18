package com.sugarspoon.chatgpt.components.loading

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sugarspoon.chatgpt.components.dialog.DialogState
import com.sugarspoon.chatgpt.components.dialog.GenericDialog

@Composable
fun Loading(
    state: DialogState
) {
    GenericDialog(state = state, onDismissListener = {}) {
        CircularProgressIndicator(
            modifier = Modifier.size(72.dp)
        )
    }
}