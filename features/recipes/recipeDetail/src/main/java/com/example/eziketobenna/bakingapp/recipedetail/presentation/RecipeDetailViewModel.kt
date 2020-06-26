package com.example.eziketobenna.bakingapp.recipedetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.MVIPresenter
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

class RecipeDetailViewModel @Inject constructor(
    private val recipeDetailActionProcessor: ActionProcessor<RecipeDetailViewAction, RecipeDetailViewResult>,
    private val recipeDetailIntentProcessor: IntentProcessor<RecipeDetailViewIntent, RecipeDetailViewAction>,
    private val recipeDetailStateReducer: ViewStateReducer<RecipeDetailViewState, RecipeDetailViewResult>
) : ViewModel(), MVIPresenter<RecipeDetailViewIntent, RecipeDetailViewState> {

    private val recipeDetailViewState: MutableStateFlow<RecipeDetailViewState> =
        MutableStateFlow(RecipeDetailViewState.Idle)

    private val actionsFlow: MutableStateFlow<RecipeDetailViewAction> =
        MutableStateFlow(RecipeDetailViewAction.Idle)

    init {
        processActions()
    }

    override fun processIntent(intents: Flow<RecipeDetailViewIntent>) {
        intents.onEach {
            actionsFlow.value = recipeDetailIntentProcessor.intentToAction(it)
        }.launchIn(viewModelScope)
    }

    private fun processActions() {
        actionsFlow
            .flatMapMerge {
                recipeDetailActionProcessor.actionToResult(it)
            }
            .scan(RecipeDetailViewState.Idle) { previous: RecipeDetailViewState, result ->
                recipeDetailStateReducer.reduce(previous, result)
            }.distinctUntilChanged()
            .onEach { viewState ->
                recipeDetailViewState.value = viewState
            }.launchIn(viewModelScope)
    }

    override val viewState: StateFlow<RecipeDetailViewState>
        get() = recipeDetailViewState
}
