package com.sugarspoon.chatgpt.ui.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.sugarspoon.chatgpt.components.dialog.rememberDialogState
import com.sugarspoon.chatgpt.components.loading.Loading
import com.sugarspoon.chatgpt.components.message.BubbleMessage
import com.sugarspoon.chatgpt.components.text.TextFieldGpt
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val loadingDialogState = rememberDialogState()
    val listState = rememberLazyListState()
    loadingDialogState.handleState(state.loading)

    ConstraintLayout(Modifier.fillMaxSize()) {
        val (messages, textField) = createRefs()
        LazyColumn(
            modifier = Modifier
                .constrainAs(messages) {
                    top.linkTo(parent.top)
                    bottom.linkTo(textField.top)
                    height = Dimension.fillToConstraints
                },
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
                .constrainAs(textField) {
                    top.linkTo(messages.bottom)
                    bottom.linkTo(parent.bottom)
                },
            textInput = state.question,
            onValueChange = viewModel::onWriteQuestion,
            onSendClicked = viewModel::onSendClicked
        )
    }
    Loading(state = loadingDialogState)
    LaunchedEffect(key1 = state.messages.size) {
        val lastIndex = if(state.messages.isNotEmpty()) state.messages.lastIndex else 0
        launch {
            listState.animateScrollToItem(index = lastIndex)
        }
    }
}