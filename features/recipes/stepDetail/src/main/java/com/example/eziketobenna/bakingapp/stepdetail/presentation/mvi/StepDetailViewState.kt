package com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi

import com.example.eziketobenna.bakingapp.presentation.event.ViewEvent
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewState

sealed class StepDetailViewState : ViewState {
    object Idle : StepDetailViewState()
    data class Loaded(
        val stepDescription: String,
        val videoUrl: String,
        val stepIndex: Int,
        val totalSteps: Int,
        val currentPosition: Int,
        val showPrev: Boolean,
        val showNext: Boolean,
        val showVideo: Boolean
    ) : StepDetailViewState() {
        val progressText: String
            get() = "$currentPosition/$totalSteps"
    }

    data class FinishEvent(val closeEvent: ViewEvent<Unit>) : StepDetailViewState()
}
