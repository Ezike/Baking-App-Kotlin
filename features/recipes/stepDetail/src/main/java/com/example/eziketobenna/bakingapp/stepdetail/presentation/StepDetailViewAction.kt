package com.example.eziketobenna.bakingapp.stepdetail.presentation

import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewAction

sealed class StepDetailViewAction : ViewAction {
    object Idle : StepDetailViewAction()
    data class LoadInitialViewAction(val index: Int, val steps: List<Step>, val step: Step) :
        StepDetailViewAction()
}
