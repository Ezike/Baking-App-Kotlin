package com.example.eziketobenna.bakingapp.stepdetail.presentation.factory

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.presentation.event.ViewEvent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewResult.GoToNextStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewResult.GoToPreviousStepViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewResult.LoadedInitialResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewState
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewState.Loaded
import javax.inject.Inject

@FeatureScope
class StepDetailViewStateFactory @Inject constructor() {

    fun createInitialViewState(result: LoadedInitialResult): Loaded {
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
                showNext = currentPosition < result.steps.size,
                showVideo = newState.videoURL.isNotEmpty(),
                stepIndex = result.steps.indexOf(newState)
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
                showNext = currentPosition < result.steps.size,
                showVideo = newState.videoURL.isNotEmpty(),
                stepIndex = result.steps.indexOf(newState)
            )
        } else oldState
    }
}
