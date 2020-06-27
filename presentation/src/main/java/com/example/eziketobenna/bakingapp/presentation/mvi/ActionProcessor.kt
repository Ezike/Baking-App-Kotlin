package com.example.eziketobenna.bakingapp.presentation.mvi

import kotlinx.coroutines.flow.Flow

interface ActionProcessor<in A : ViewAction, out R : ViewResult> {
    fun actionToResult(viewAction: A): Flow<R>
}
