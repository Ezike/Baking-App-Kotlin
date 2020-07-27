package com.example.eziketobenna.bakingapp.recipe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.HomeStateMachine
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewState
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn

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
