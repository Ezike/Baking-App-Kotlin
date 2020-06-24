package com.example.eziketobenna.bakingapp.stepdetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailResult.GoToNextStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailResult.GoToPreviousStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailResult.LoadedInitialResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewAction.GoToNextStepViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewAction.GoToPreviousStepViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewAction.LoadInitialViewAction
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class StepDetailActionProcessor @Inject constructor() :
    ActionProcessor<StepDetailViewAction, StepDetailResult> {

    override fun actionToResult(viewAction: StepDetailViewAction): Flow<StepDetailResult> {
        return when (viewAction) {
            StepDetailViewAction.Idle -> flowOf(StepDetailResult.IdleResult)
            is LoadInitialViewAction -> loadedInitialResult(viewAction)
            is GoToNextStepViewAction -> flowOf(GoToNextStepViewResult(viewAction.steps))
            is GoToPreviousStepViewAction -> flowOf(GoToPreviousStepViewResult(viewAction.steps))
        }
    }

    private fun loadedInitialResult(viewAction: LoadInitialViewAction): Flow<LoadedInitialResult> =
        flowOf(
            LoadedInitialResult(
                viewAction.index,
                viewAction.steps,
                viewAction.step
            )
        )
}
