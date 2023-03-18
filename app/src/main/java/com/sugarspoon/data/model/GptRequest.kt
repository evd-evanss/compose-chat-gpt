package com.sugarspoon.data.model

data class GptRequest(
    val prompt: String,
    val max_tokens: Int,
    val model: String
)