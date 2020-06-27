package com.example.eziketobenna.bakingapp.presentation.mvi

interface IntentProcessor<in I : ViewIntent, out A : ViewAction> {
    fun intentToAction(intent: I): A
}
