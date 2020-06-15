package com.example.eziketobenna.bakingapp.presentation.mvi

interface IntentProcessor<I : ViewIntent, A : ViewAction> {
    fun actionFromIntent(intent: I): A
}
