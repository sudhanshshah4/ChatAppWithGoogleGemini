package com.genai.chatapp

object KeyProvider {
    init {
        System.loadLibrary("keys")
    }
    external fun getApiKey():String
}