package com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi

import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewAction

sealed class StepDetailViewAction : ViewAction {
    object Idle : StepDetailViewAction()
    data class LoadInitialViewAction(val index: Int, val steps: List<Step>, val step: Step) :
        StepDetailViewAction()

    data class GoToNextStepViewAction(val steps: List<Step>) : StepDetailViewAction()
    data class GoToPreviousStepViewAction(val steps: List<Step>) : StepDetailViewAction()
}
