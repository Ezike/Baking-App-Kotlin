package com.example.eziketobenna.bakingapp.presentation

import kotlinx.coroutines.flow.Flow

interface MVIViewModel<I : ViewIntent, S : ViewState> {
    fun processIntent(intents: Flow<I>)
    fun states(): Flow<S>
}
