package com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi

import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewResult

sealed class StepDetailViewResult(open val steps: List<Step>) : ViewResult {
    object IdleResult : StepDetailViewResult(emptyList())
    data class LoadedInitialResult(val stepIndex: Int, override val steps: List<Step>, val currentStep: Step) :
        StepDetailViewResult(steps)
    data class GoToNextStepViewResult(override val steps: List<Step>) : StepDetailViewResult(steps)
    data class GoToPreviousStepViewResult(override val steps: List<Step>) : StepDetailViewResult(steps)
}
