package com.example.eziketobenna.bakingapp.stepdetail.presentation

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.presentation.event.ViewEvent
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewResult.GoToNextStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewResult.GoToPreviousStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewResult.LoadedInitialResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewState.FinishEvent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewState.Loaded
import javax.inject.Inject

@FeatureScope
class StepDetailViewStateReducer @Inject constructor() :
    ViewStateReducer<StepDetailViewState, StepDetailViewResult> {

    override fun reduce(
        previous: StepDetailViewState,
        result: StepDetailViewResult
    ): StepDetailViewState {

        return when (result) {
            StepDetailViewResult.IdleResult -> StepDetailViewState.Idle
            is LoadedInitialResult -> loadInitialViewState(result)
            is GoToNextStepViewResult -> loadNextStepViewState(previous, result)
            is GoToPreviousStepViewResult -> loadPreviousStepViewState(previous, result)
        }
    }

    private fun loadPreviousStepViewState(
        oldState: StepDetailViewState,
        result: GoToPreviousStepViewResult
    ): StepDetailViewState {
        if (oldState is StepDetailViewState.Idle) {
            return oldState
        }

        oldState as Loaded

        return if (oldState.stepIndex > 0) {
            val currentPosition: Int = oldState.currentPosition - 1
            val newState: Step = result.steps[oldState.stepIndex - 1]
            Loaded(
                stepDescription = newState.description,
                videoUrl = newState.videoURL,
                totalSteps = result.steps.size,
                currentPosition = currentPosition,
                showPrev = currentPosition > 1,
                showNext = currentPosition < result.steps.size,
                showVideo = newState.videoURL.isNotEmpty(),
                stepIndex = result.steps.indexOf(newState)
            )
        } else oldState
    }

    private fun loadNextStepViewState(
        oldState: StepDetailViewState,
        result: GoToNextStepViewResult
    ): StepDetailViewState {
        if (oldState is StepDetailViewState.Idle) {
            return oldState
        }

        oldState as Loaded

        return if (oldState.stepIndex < result.steps.lastIndex) {
            val currentPosition: Int = oldState.currentPosition + 1
            val newState: Step = result.steps[oldState.stepIndex + 1]
            Loaded(
                stepDescription = newState.description,
                videoUrl = newState.videoURL,
                totalSteps = result.steps.size,
                currentPosition = currentPosition,
                showPrev = currentPosition > 1,
                showNext = currentPosition < result.steps.size,
                showVideo = newState.videoURL.isNotEmpty(),
                stepIndex = result.steps.indexOf(newState)
            )
        } else FinishEvent(ViewEvent(Unit))
    }

    private fun loadInitialViewState(result: LoadedInitialResult): Loaded {
        val currentPosition: Int = result.stepIndex + 1
        return Loaded(
            stepDescription = result.step.description,
            videoUrl = result.step.videoURL,
            totalSteps = result.steps.size,
            currentPosition = currentPosition,
            showPrev = currentPosition > 1,
            showNext = currentPosition < result.steps.size,
            showVideo = result.step.videoURL.isNotEmpty(),
            stepIndex = result.stepIndex
        )
    }
}
