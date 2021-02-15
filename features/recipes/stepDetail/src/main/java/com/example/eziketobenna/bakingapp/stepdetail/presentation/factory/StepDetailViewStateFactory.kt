package com.example.eziketobenna.bakingapp.stepdetail.presentation.factory

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.presentation.event.ViewEvent
import com.example.eziketobenna.bakingapp.stepdetail.R
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewResult.GoToNextStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewResult.GoToPreviousStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewResult.LoadedInitialResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewState
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewState.Loaded
import javax.inject.Inject

@FeatureScope
class StepDetailViewStateFactory @Inject constructor() {

    fun createInitialViewState(
        oldState: StepDetailViewState,
        result: LoadedInitialResult
    ): Loaded {
        val currentPosition: Int = result.stepIndex + 1
        return Loaded(
            stepDescription = result.currentStep.description,
            videoUrl = result.currentStep.videoURL,
            totalSteps = result.steps.size,
            currentPosition = currentPosition,
            showPrev = currentPosition > 1,
            nextButtonText = nextButtonText(currentPosition, result.steps),
            showVideo = result.currentStep.videoURL.isNotEmpty(),
            stepIndex = result.stepIndex,
            toolbarTitle = oldState.toolbarTitle
        )
    }

    private fun nextButtonText(
        currentPosition: Int,
        result: List<Step>
    ): Int = if (currentPosition < result.size) R.string.next else R.string.finish

    fun makeNextStep(
        oldState: Loaded,
        result: GoToNextStepViewResult
    ): StepDetailViewState {
        return if (oldState.stepIndex < result.steps.lastIndex) {
            val currentPosition: Int = oldState.currentPosition + 1
            val newState: Step = result.steps[oldState.stepIndex + 1]
            Loaded(
                stepDescription = newState.description,
                videoUrl = newState.videoURL,
                totalSteps = result.steps.size,
                currentPosition = currentPosition,
                showPrev = currentPosition > 1,
                nextButtonText = nextButtonText(currentPosition, result.steps),
                showVideo = newState.videoURL.isNotEmpty(),
                stepIndex = result.steps.indexOf(newState),
                toolbarTitle = newState.shortDescription
            )
        } else StepDetailViewState.FinishEvent(ViewEvent(Unit))
    }

    fun makePreviousStep(
        oldState: Loaded,
        result: GoToPreviousStepViewResult
    ): Loaded {
        return if (oldState.stepIndex > 0) {
            val currentPosition: Int = oldState.currentPosition - 1
            val newState: Step = result.steps[oldState.stepIndex - 1]
            Loaded(
                stepDescription = newState.description,
                videoUrl = newState.videoURL,
                totalSteps = result.steps.size,
                currentPosition = currentPosition,
                showPrev = currentPosition > 1,
                nextButtonText = nextButtonText(currentPosition, result.steps),
                showVideo = newState.videoURL.isNotEmpty(),
                stepIndex = result.steps.indexOf(newState),
                toolbarTitle = newState.shortDescription
            )
        } else oldState
    }
}
