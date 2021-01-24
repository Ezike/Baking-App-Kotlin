package com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi

import com.example.eziketobenna.bakingapp.model.StepInfoModel
import com.example.eziketobenna.bakingapp.presentation.mvi.StateMachine
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepActionProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepIntentProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepViewStateReducer
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class StepDetailStateMachine @AssistedInject constructor(
    private val intentProcessor: StepIntentProcessor,
    actionProcessor: StepActionProcessor,
    stateReducer: StepViewStateReducer,
    @Assisted private val stepInfo: StepInfoModel,
    @Assisted threader: ThreadUtil,
) : StateMachine<StepDetailViewAction, StepDetailViewIntent,
    StepDetailViewState, StepDetailViewResult>(
    actionProcessor,
    intentProcessor,
    stateReducer,
    intentProcessor.intentToAction(
        StepDetailViewIntent.LoadInitialViewIntent(stepInfo)
    ),
    StepDetailViewState.Idle,
    threader
) {
    @AssistedFactory
    interface Factory {
        fun create(stepInfo: StepInfoModel, threader: ThreadUtil): StepDetailStateMachine
    }
}
