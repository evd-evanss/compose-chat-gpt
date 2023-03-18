package com.sugarspoon.chatgpt.ui.chat

import com.sugarspoon.chatgpt.base.ScreenState
import com.sugarspoon.chatgpt.components.message.MessageModel

data class ChatState(
    val messages: List<MessageModel> = listOf(),
    val question: String = "",
    val loading: Boolean = false,
    val errorMessage: String = "",
    val showError: Boolean = false,
): ScreenState
