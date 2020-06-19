package com.example.eziketobenna.bakingapp.presentation.mvi

import kotlinx.coroutines.flow.Flow

interface ActionProcessor<A : ViewAction, R : ViewResult> {
    fun actionToResult(viewAction: A): Flow<R>
}
