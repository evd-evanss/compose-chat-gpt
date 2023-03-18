package com.sugarspoon.chatgpt.ui.chat

import com.sugarspoon.chatgpt.base.ScreenEvent

sealed class ChatEvents : ScreenEvent {
    object OnSendQuestion: ChatEvents()
    data class OnWriteQuestion(val question: String): ChatEvents()
}
