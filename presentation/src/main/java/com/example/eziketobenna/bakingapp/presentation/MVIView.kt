package com.example.eziketobenna.bakingapp.presentation

import kotlinx.coroutines.flow.Flow

interface MVIView<I : ViewIntent, in S : ViewState> {
    fun render(state: S)
    fun intents(): Flow<I>
}
