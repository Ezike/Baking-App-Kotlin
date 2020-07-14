package com.example.eziketobenna.bakingapp.stepdetail.presentation

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

class StepDetailViewModel @Inject constructor(
    private val stepDetailIntentProcessor: StepIntentProcessor,
    private val stepDetailActionProcessor: StepActionProcessor,
    private val viewStateReducer: StepViewStateReducer
) : ViewModel(), MVIPresenter<StepDetailViewIntent, StepDetailViewState> {

    private val stepDetailViewState: MutableStateFlow<StepDetailViewState> =
        MutableStateFlow(StepDetailViewState.Idle)

    private val actionsChannel =
        ConflatedBroadcastChannel<StepDetailViewAction>(StepDetailViewAction.Idle)

    override val viewState: StateFlow<StepDetailViewState>
        get() = stepDetailViewState

    init {
        processActions()
    }

    override fun processIntent(intents: Flow<StepDetailViewIntent>) {
        intents.onEach { intent ->
            actionsChannel.offer(stepDetailIntentProcessor.intentToAction(intent))
        }.launchIn(viewModelScope)
    }

    private fun processActions() {
        actionsChannel.asFlow()
            .flatMapMerge { viewAction ->
                stepDetailActionProcessor.actionToResult(viewAction)
            }.scan(StepDetailViewState.Idle) { previous: StepDetailViewState, result ->
                viewStateReducer.reduce(previous, result)
            }.distinctUntilChanged()
            .onEach { viewState ->
                stepDetailViewState.value = viewState
            }.launchIn(viewModelScope)
    }
}
