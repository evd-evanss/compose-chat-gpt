package com.sugarspoon.data.exceptions

class ChatGptExceptions(message: String): java.lang.Exception(message) {

    companion object {

        fun extractException(exception: java.lang.Exception): java.lang.Exception {
            return when {
                exception.message.orEmpty().contains("401") -> {
                    ChatGptExceptions("Usuário inválido, faça login para continuar.")
                }
                exception.message.orEmpty().contains("429") -> {
                    ChatGptExceptions("Seu período de teste acabou, que pena :(")
                }
                exception.message.orEmpty().contains("Unable to resolve host") -> {
                    ChatGptExceptions("Não foi possível enviar sua requisição, verifique sua internet.")
                }
                else -> {
                    ChatGptExceptions("Estamos com problemas no momento, tente novamente mais tarde.")
                }
            }
        }
    }
}