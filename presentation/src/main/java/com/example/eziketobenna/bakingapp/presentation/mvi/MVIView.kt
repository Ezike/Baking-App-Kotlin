package com.example.eziketobenna.bakingapp.presentation.mvi

interface MVIView<out I : ViewIntent, in S : ViewState> {
    fun render(state: S)
}
