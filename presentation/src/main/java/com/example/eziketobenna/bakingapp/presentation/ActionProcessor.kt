package com.example.eziketobenna.bakingapp.presentation

import kotlinx.coroutines.flow.Flow

interface ActionProcessor<A : ViewAction, R : ViewResult> {
    fun actionToResultProcessor(a: A): Flow<R>
}
