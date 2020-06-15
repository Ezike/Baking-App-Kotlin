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
        MutableStateFlow(RecipeViewState.IDLE)

    private val actionFlow: MutableStateFlow<RecipeViewAction> =
        MutableStateFlow(RecipeViewAction.LoadInitialAction)

    override fun processIntent(intents: Flow<RecipeViewIntent>) {
        intents.onEach { intent ->
            actionFlow.value = recipeViewIntentProcessor.actionFromIntent(intent)
        }.launchIn(viewModelScope)
    }

    fun processActions() {
        actionFlow.flatMapMerge { action ->
            recipeActionProcessor.actionToResultProcessor(action)
        }.scan(RecipeViewState.IDLE) { previous: RecipeViewState, result: RecipeViewResult ->
            recipeViewStateReducer.reduce(previous, result)
        }.distinctUntilChanged().onEach { recipeViewState ->
            _recipeViewState.value = recipeViewState
        }.launchIn(viewModelScope)
    }

    override val viewState: Flow<RecipeViewState>
        get() = _recipeViewState
}
