package com.example.eziketobenna.bakingapp.stepdetail.presentation

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewResult.GoToNextStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewResult.GoToPreviousStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewResult.LoadedInitialResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewState.FinishEvent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewState.Idle
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewState.Loaded
import com.example.eziketobenna.bakingapp.stepdetail.presentation.factory.StepDetailViewStateFactory
import javax.inject.Inject

@FeatureScope
class StepDetailViewStateReducer @Inject constructor(
    private val stepDetailViewStateFactory: StepDetailViewStateFactory
) : ViewStateReducer<StepDetailViewState, StepDetailViewResult> {

    override fun reduce(
        previous: StepDetailViewState,
        result: StepDetailViewResult
    ): StepDetailViewState {

        return when (result) {
            StepDetailViewResult.IdleResult -> Idle
            is LoadedInitialResult -> loadInitialViewState(result)
            is GoToNextStepViewResult -> loadNextStepViewState(previous, result)
            is GoToPreviousStepViewResult -> loadPreviousStepViewState(previous, result)
        }
    }

    private fun loadPreviousStepViewState(
        oldState: StepDetailViewState,
        result: GoToPreviousStepViewResult
    ): StepDetailViewState {
        return when (oldState) {
            Idle, is FinishEvent -> oldState
            is Loaded -> stepDetailViewStateFactory.makePreviousStep(oldState, result)
        }
    }

    private fun loadNextStepViewState(
        oldState: StepDetailViewState,
        result: GoToNextStepViewResult
    ): StepDetailViewState {

        return when (oldState) {
            Idle, is FinishEvent -> oldState
            is Loaded -> stepDetailViewStateFactory.makeNextStep(oldState, result)
        }
    }

    private fun loadInitialViewState(result: LoadedInitialResult): Loaded =
        stepDetailViewStateFactory.createInitialViewState(result)
}
