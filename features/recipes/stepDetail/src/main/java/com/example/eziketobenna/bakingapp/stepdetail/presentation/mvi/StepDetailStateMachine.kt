package com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi

import com.example.eziketobenna.bakingapp.presentation.mvi.StateMachine
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepActionProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepIntentProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepViewStateReducer
import javax.inject.Inject

class StepDetailStateMachine @Inject constructor(
    intentProcessor: StepIntentProcessor,
    actionProcessor: StepActionProcessor,
    stateReducer: StepViewStateReducer
) : StateMachine<StepDetailViewAction, StepDetailViewIntent,
    StepDetailViewState, StepDetailViewResult>(
    actionProcessor,
    intentProcessor,
    stateReducer,
    StepDetailViewAction.Idle,
    StepDetailViewState.Idle
)
