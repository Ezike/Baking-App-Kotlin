package com.example.eziketobenna.bakingapp.recipe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.HomeStateMachine
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeViewModel @Inject constructor(
    private val homeStateMachine: HomeStateMachine
) : ViewModel() {

    val viewState: StateFlow<RecipeViewState>
        get() = homeStateMachine.viewState

    fun processIntent(intents: RecipeViewIntent) {
        homeStateMachine.processIntent(intents)
    }

    override fun onCleared() {
        homeStateMachine.unSubscribe()
        super.onCleared()
    }
}
