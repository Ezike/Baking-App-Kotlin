package com.example.eziketobenna.bakingapp.stepdetail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailStateMachine
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject

class StepDetailViewModel @Inject constructor(
    private val stepDetailStateMachine: StepDetailStateMachine
) : ViewModel() {

    val viewState: StateFlow<StepDetailViewState> = stepDetailStateMachine.viewState

    init {
        stepDetailStateMachine.processor.launchIn(viewModelScope)
    }

    fun processIntent(intents: Flow<StepDetailViewIntent>) {
        stepDetailStateMachine
            .processIntents(intents)
            .launchIn(viewModelScope)
    }
}
