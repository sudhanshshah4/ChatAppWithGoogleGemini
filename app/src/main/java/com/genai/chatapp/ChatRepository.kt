package com.genai.chatapp

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChatRepository {
    fun getBotResponse(message: String): Flow<String> = flow {
        delay(1000) // Simulate network delay
        emit("Bot Response for: $message")
    }
}