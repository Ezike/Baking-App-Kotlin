package com.example.eziketobenna.bakingapp.stepdetail.presentation

import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewResult

sealed class StepDetailResult : ViewResult {
    object IdleResult : StepDetailResult()
    data class LoadedInitialResult(val stepIndex: Int, val steps: List<Step>, val step: Step) :
        StepDetailResult()
    data class GoToNextStepViewResult(val steps: List<Step>) : StepDetailResult()
    data class GoToPreviousStepViewResult(val steps: List<Step>) : StepDetailResult()
}
