package com.example.eziketobenna.bakingapp.stepdetail.presentation.processor

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewAction.GoToNextStepViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewAction.GoToPreviousStepViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewAction.LoadInitialViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewResult.GoToNextStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewResult.GoToPreviousStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewResult.LoadedInitialResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

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
                viewAction.currentStep
            )
        )
}
