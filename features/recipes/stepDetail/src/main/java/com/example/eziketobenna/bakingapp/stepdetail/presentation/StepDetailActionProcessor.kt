package com.example.eziketobenna.bakingapp.stepdetail.presentation

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewAction.GoToNextStepViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewAction.GoToPreviousStepViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewAction.LoadInitialViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewResult.GoToNextStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewResult.GoToPreviousStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewResult.LoadedInitialResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@FeatureScope
class StepDetailActionProcessor @Inject constructor() :
    ActionProcessor<StepDetailViewAction, StepDetailViewResult> {

    override fun actionToResult(viewAction: StepDetailViewAction): Flow<StepDetailViewResult> {
        return when (viewAction) {
            StepDetailViewAction.Idle -> flowOf(StepDetailViewResult.IdleResult)
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
