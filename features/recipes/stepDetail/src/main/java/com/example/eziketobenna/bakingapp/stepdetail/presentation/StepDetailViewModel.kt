package com.example.eziketobenna.bakingapp.stepdetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class StepDetailViewModel @Inject constructor(
    private val stepDetailIntentProcessor: StepDetailIntentProcessor,
    private val stepDetailActionProcessor: StepDetailActionProcessor,
    private val viewStateReducer: StepDetailViewStateReducer
) : ViewModel(), MVIPresenter<StepDetailViewIntent, StepDetailViewState> {

    private val stepDetailViewState: MutableStateFlow<StepDetailViewState> =
        MutableStateFlow(StepDetailViewState.Idle)

    private val actionsFlow: MutableStateFlow<StepDetailViewAction> =
        MutableStateFlow(StepDetailViewAction.Idle)

    override val viewState: StateFlow<StepDetailViewState>
        get() = stepDetailViewState

    init {
        processActions()
    }

    override fun processIntent(intents: Flow<StepDetailViewIntent>) {
        intents.onEach {
            actionsFlow.value = stepDetailIntentProcessor.intentToAction(it)
        }.launchIn(viewModelScope)
    }

    private fun processActions() {
        actionsFlow
            .flatMapMerge {
                stepDetailActionProcessor.actionToResult(it)
            }.scan(StepDetailViewState.Idle) { previous: StepDetailViewState, result ->
                viewStateReducer.reduce(previous, result)
            }.distinctUntilChanged()
            .onEach { viewState ->
                stepDetailViewState.value = viewState
            }.launchIn(viewModelScope)
    }
}
