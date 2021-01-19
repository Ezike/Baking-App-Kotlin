package com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi

import com.example.eziketobenna.bakingapp.model.StepInfoModel
import com.example.eziketobenna.bakingapp.presentation.mvi.StateMachine
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepActionProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepIntentProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepViewStateReducer
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

class StepDetailStateMachine @AssistedInject constructor(
    intentProcessor: StepIntentProcessor,
    actionProcessor: StepActionProcessor,
    stateReducer: StepViewStateReducer,
    @Assisted stepInfo: StepInfoModel
) : StateMachine<StepDetailViewAction, StepDetailViewIntent,
    StepDetailViewState, StepDetailViewResult>(
    actionProcessor,
    intentProcessor,
    stateReducer,
    intentProcessor.intentToAction(StepDetailViewIntent.LoadInitialViewIntent(stepInfo)),
    StepDetailViewState.Idle
) {
    @AssistedFactory
    interface Factory {
        fun create(stepInfo: StepInfoModel): StepDetailStateMachine
    }
}
