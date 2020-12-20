package com.example.eziketobenna.bakingapp.stepdetail.presentation

import androidx.lifecycle.ViewModel
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailStateMachine
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class StepDetailViewModel @Inject constructor(
    private val stepDetailStateMachine: StepDetailStateMachine
) : ViewModel() {

    val viewState: StateFlow<StepDetailViewState> = stepDetailStateMachine.viewState

    fun processIntent(intents: Flow<StepDetailViewIntent>) {
        stepDetailStateMachine.processIntents(intents)
    }

    override fun onCleared() {
        stepDetailStateMachine.unSubscribe()
        super.onCleared()
    }
}
