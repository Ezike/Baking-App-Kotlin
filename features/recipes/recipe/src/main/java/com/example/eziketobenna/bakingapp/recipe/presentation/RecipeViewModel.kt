package com.example.eziketobenna.bakingapp.recipe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.HomeStateMachine
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class RecipeViewModel @Inject constructor(
    private val homeStateMachine: HomeStateMachine
) : ViewModel() {

    val viewState: StateFlow<RecipeViewState> = homeStateMachine.viewState

    init {
        homeStateMachine.processor.launchIn(viewModelScope)
    }

    fun processIntent(intents: Flow<RecipeViewIntent>) {
        homeStateMachine
            .processIntents(intents)
            .launchIn(viewModelScope)
    }
}
