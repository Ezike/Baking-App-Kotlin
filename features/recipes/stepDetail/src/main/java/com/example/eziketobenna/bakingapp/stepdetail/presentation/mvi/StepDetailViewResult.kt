package com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi

import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewResult

sealed class StepDetailViewResult : ViewResult {
    object IdleResult : StepDetailViewResult()
    data class LoadedInitialResult(val stepIndex: Int, val steps: List<Step>, val step: Step) :
        StepDetailViewResult()
    data class GoToNextStepViewResult(val steps: List<Step>) : StepDetailViewResult()
    data class GoToPreviousStepViewResult(val steps: List<Step>) : StepDetailViewResult()
}
