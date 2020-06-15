package com.example.eziketobenna.bakingapp.presentation.mvi

interface ViewStateReducer<S : ViewState, R : ViewResult> {
    fun reduce(previous: S, result: R): S
}
