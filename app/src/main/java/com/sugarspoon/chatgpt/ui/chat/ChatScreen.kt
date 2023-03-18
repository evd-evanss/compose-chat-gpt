package com.sugarspoon.chatgpt.ui.chat

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sugarspoon.chatgpt.components.dialog.rememberDialogState
import com.sugarspoon.chatgpt.components.loading.Loading
import com.sugarspoon.chatgpt.components.message.BubbleMessage
import com.sugarspoon.chatgpt.components.text.TextFieldGpt
import com.sugarspoon.chatgpt.theme.Dimens.SpacingXXS
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
    configuration: Configuration = LocalConfiguration.current
) {
    val state by viewModel.state.collectAsState()
    val loadingDialogState = rememberDialogState()
    val listState = rememberLazyListState()
    val errorDialogState = rememberDialogState()
    loadingDialogState.handleState(state.loading)
    errorDialogState.handleState(state.showError)

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .height((configuration.screenHeightDp * PERCENTAGE).dp)
                .padding(bottom = SpacingXXS),
            state = listState
        ) {
            items(
                count = state.messages.size,
                itemContent = { index ->
                    BubbleMessage(message = state.messages[index])
                }
            )
        }
        TextFieldGpt(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            textInput = state.question,
            onValueChange = viewModel::onWriteQuestion,
            onSendClicked = viewModel::onSendClicked
        )
    }
    Loading(state = loadingDialogState)
    LaunchedEffect(key1 = state.messages.size) {
        launch {
            listState.animateScrollToItem(index = state.messages.size)
        }
    }
}

private const val PERCENTAGE = 0.80f