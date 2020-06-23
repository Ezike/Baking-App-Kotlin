package com.example.eziketobenna.bakingapp.recipe.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eziketobenna.bakingapp.presentation.mvi.MVIPresenter
import javax.inject.Inject
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

class RecipeViewModel @Inject constructor(
    private val recipeActionProcessor: RecipeActionProcessor,
    private val recipeViewStateReducer: RecipeViewStateReducer,
    private val recipeViewIntentProcessor: RecipeViewIntentProcessor
) : ViewModel(), MVIPresenter<RecipeViewIntent, RecipeViewState> {

    private val _recipeViewState: MutableStateFlow<RecipeViewState> =
        MutableStateFlow(RecipeViewState.init)

    override val viewState: StateFlow<RecipeViewState>
        get() = _recipeViewState

    /** Using a channel cos [MutableStateFlow] doesn't emit subsequent values of the same type */
    private val actionsChannel =
        ConflatedBroadcastChannel<RecipeViewAction>(RecipeViewAction.LoadInitialAction)

    private val actionFlow: Flow<RecipeViewAction>
        get() = actionsChannel.asFlow()

    init {
        processActions()
    }

    override fun processIntent(intents: Flow<RecipeViewIntent>) {
        intents.onEach { intent ->
            actionsChannel.offer(recipeViewIntentProcessor.intentToAction(intent))
        }.launchIn(viewModelScope)
    }

    private fun processActions() {
        actionFlow
            .flatMapMerge { action ->
                recipeActionProcessor.actionToResult(action)
            }.scan(RecipeViewState.init) { previous, result ->
                recipeViewStateReducer.reduce(previous, result)
            }.distinctUntilChanged()
            .onEach { recipeViewState ->
                _recipeViewState.value = recipeViewState
            }.launchIn(viewModelScope)
    }
}
