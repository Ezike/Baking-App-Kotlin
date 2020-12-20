package com.example.eziketobenna.bakingapp.recipe.presentation

import androidx.lifecycle.ViewModel
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.HomeStateMachine
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RecipeViewModel @Inject constructor(
    private val homeStateMachine: HomeStateMachine
) : ViewModel() {

    val viewState: StateFlow<RecipeViewState>
        get() = homeStateMachine.viewState

    fun processIntent(intents: Flow<RecipeViewIntent>) {
        homeStateMachine.processIntents(intents)
    }

    override fun onCleared() {
        homeStateMachine.unSubscribe()
        super.onCleared()
    }
}
