package com.example.eziketobenna.bakingapp.recipe.presentation

import androidx.lifecycle.ViewModel
import com.example.eziketobenna.bakingapp.presentation_android.stateMachineThreader
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.HomeStateMachine
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewState
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RecipeViewModel @Inject constructor(
    homeStateMachineFactory: HomeStateMachine.Factory
) : ViewModel() {

    private val homeStateMachine =
        homeStateMachineFactory.create(stateMachineThreader)

    val viewState: StateFlow<RecipeViewState>
        get() = homeStateMachine.viewState

    fun processIntent(intents: RecipeViewIntent) {
        homeStateMachine.processIntent(intents)
    }
}
