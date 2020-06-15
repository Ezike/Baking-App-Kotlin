package com.example.eziketobenna.bakingapp.presentation.mvi

import kotlinx.coroutines.flow.Flow

interface MVIViewModel<I : ViewIntent, S : ViewState> {
    fun processIntent(intents: Flow<I>)
    val viewState: Flow<S>
}
