package com.example.eziketobenna.bakingapp.stepdetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.stepdetail.model.StepUiModel
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewState.Loaded
import javax.inject.Inject

class StepDetailViewStateReducer @Inject constructor() :
    ViewStateReducer<StepDetailViewState, StepDetailResult> {

    override fun reduce(
        previous: StepDetailViewState,
        result: StepDetailResult
    ): StepDetailViewState {

        return when (result) {
            StepDetailResult.IdleResult -> StepDetailViewState.Idle
            is StepDetailResult.LoadedInitialResult -> loadedViewState(result)
        }
    }

    private fun loadedViewState(result: StepDetailResult.LoadedInitialResult): Loaded {
        val uiModel = StepUiModel(
            stepDescription = result.step.description,
            videoUrl = result.step.videoURL,
            totalSteps = result.steps.size,
            stepIndex = result.stepIndex,
            showPrev = result.stepIndex > 1,
            showNext = result.stepIndex < result.steps.size,
            showVideo = result.step.videoURL.isNotEmpty()
        )
        return Loaded(uiModel)
    }
}
