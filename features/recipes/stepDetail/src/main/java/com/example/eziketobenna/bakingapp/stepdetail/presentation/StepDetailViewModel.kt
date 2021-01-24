package com.example.eziketobenna.bakingapp.stepdetail.presentation

import androidx.lifecycle.ViewModel
import com.example.eziketobenna.bakingapp.core.factory.AssistedViewModelFactory
import com.example.eziketobenna.bakingapp.model.StepInfoModel
import com.example.eziketobenna.bakingapp.presentation_android.stateMachineThreader
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailStateMachine
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.StateFlow

class StepDetailViewModel @AssistedInject constructor(
    private val factory: StepDetailStateMachine.Factory,
    @Assisted private val stepInfoModel: StepInfoModel
) : ViewModel() {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<StepInfoModel> {
        override fun create(data: StepInfoModel): StepDetailViewModel
    }

    private val stepDetailStateMachine =
        factory.create(stepInfoModel, stateMachineThreader)

    val viewState: StateFlow<StepDetailViewState>
        get() = stepDetailStateMachine.viewState

    fun processIntent(intents: StepDetailViewIntent) {
        stepDetailStateMachine.processIntent(intents)
    }
}
