package com.genai.chatapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.asTextOrNull
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel() : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages: StateFlow<List<Message>> = _messages

    private val model = GenerativeModel(
        "gemini-1.5-flash",
        BuildConfig.GeminiApiKey,
        generationConfig = generationConfig {
            temperature = 2f
            topK = 64
            topP = 0.95f
            maxOutputTokens = 8192
            responseMimeType = "text/plain"
        }
    )

    fun sendMessage(userInput: String) {
        viewModelScope.launch {
            // Add the user's message to the list
            val userMessage = Message(content = userInput, isUser = true)
            _messages.value += userMessage

            // Generate bot response based on the user's message
            val response = model.generateContent(
                content {
                    text("input: Hi")
                    text("output: Hi")
                    text("input: How are you doing")
                    text("output: I am doing good")
                    text("input: What are you doing")
                    text("output: I am learning training model and using it with Gemini")
                    text("input: What tools are you using")
                    text("output: I am using Google AI Studio for model training and Android")
                    text("input: $userInput")
                }
            )
            // Get the bot's response
            val botResponse =
                response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.asTextOrNull()
                    ?: "Sorry, I didn't understand that."
            Log.i("botResponse: ", botResponse)
            // Add the bot's message to the list
            val botMessage = Message(content = botResponse, isUser = false)
            _messages.value += botMessage
        }
    }

}

// Data class to represent messages
data class Message(val content: String, val isUser: Boolean)