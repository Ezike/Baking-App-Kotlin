package com.example.eziketobenna.bakingapp.recipedetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eziketobenna.bakingapp.navigation.NavigationDispatcher
import com.example.eziketobenna.bakingapp.presentation.mvi.MVIPresenter
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
    private val recipeDetailActionProcessor: DetailActionProcessor,
    private val recipeDetailIntentProcessor: DetailIntentProcessor,
    private val recipeDetailStateReducer: DetailViewStateReducer,
    private val navigationDispatcher: NavigationDispatcher
) : ViewModel(), MVIPresenter<RecipeDetailViewIntent, RecipeDetailViewState>,
    NavigationDispatcher by navigationDispatcher {

    private val actionsFlow: MutableStateFlow<RecipeDetailViewAction> =
        MutableStateFlow(RecipeDetailViewAction.Idle)

    private val recipeDetailViewState: MutableStateFlow<RecipeDetailViewState> =
        MutableStateFlow(RecipeDetailViewState.Idle)

    override val viewState: StateFlow<RecipeDetailViewState>
        get() = recipeDetailViewState

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
}
