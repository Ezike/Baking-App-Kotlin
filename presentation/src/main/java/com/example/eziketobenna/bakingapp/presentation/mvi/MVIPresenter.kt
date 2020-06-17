package com.example.eziketobenna.bakingapp.presentation.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MVIPresenter<in I : ViewIntent, out S : ViewState> {
    fun processIntent(intents: Flow<I>)
    val viewState: StateFlow<S>
}
