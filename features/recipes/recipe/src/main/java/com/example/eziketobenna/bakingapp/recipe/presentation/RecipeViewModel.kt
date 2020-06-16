package com.example.eziketobenna.bakingapp.recipe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eziketobenna.bakingapp.presentation.mvi.MVIViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

class RecipeViewModel @Inject constructor(
    private val recipeActionProcessor: RecipeActionProcessor,
    private val recipeViewStateReducer: RecipeViewStateReducer,
    private val recipeViewIntentProcessor: RecipeViewIntentProcessor
) : ViewModel(), MVIViewModel<RecipeViewIntent, RecipeViewState> {

    private val _recipeViewState: MutableStateFlow<RecipeViewState> =
        MutableStateFlow(RecipeViewState.Initial)

    private val actionFlow: MutableStateFlow<RecipeViewAction> =
        MutableStateFlow(RecipeViewAction.LoadInitialAction)

    init {
        processActions()
    }

    override fun processIntent(intents: Flow<RecipeViewIntent>) {
        intents.onEach { intent ->
            actionFlow.value = recipeViewIntentProcessor.intentToAction(intent)
        }.launchIn(viewModelScope)
    }

    private fun processActions() {
        actionFlow.flatMapMerge { action ->
            recipeActionProcessor.actionToResultProcessor(action)
        }.scan(RecipeViewState.Initial) { previous: RecipeViewState, result: RecipeViewResult ->
            recipeViewStateReducer.reduce(previous, result)
        }.distinctUntilChanged().onEach { recipeViewState ->
            _recipeViewState.value = recipeViewState
        }.launchIn(viewModelScope)
    }

    override val viewState: Flow<RecipeViewState>
        get() = _recipeViewState
}
