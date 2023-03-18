package com.sugarspoon.data.utils

import com.sugarspoon.chatgpt.BuildConfig

object Constants {

    const val CONTENT_TYPE = "Content-Type: application/json"
    const val AUTHORIZATION = "Authorization: Bearer ${BuildConfig.API_KEY}"
    const val PATH_COMPLETIONS = "/v1/completions"
    const val BASE_URL = "https://api.openai.com"
    const val TIME_FORMAT = "HH:mm"
    const val REQUEST_MODEL = "text-davinci-003"
    const val MAX_TOKENS = 300


    const val CHAT_GPT = "Open Ai - ChatGpt"
    const val CHAT_LABEL_USER = "Você"
    const val CHAT_ERROR_MESSAGE = "Volte aqui outro dia com esta pergunta, preciso de tempo pra pensar melhor..."

}