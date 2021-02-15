package com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi

import androidx.annotation.StringRes
import com.example.eziketobenna.bakingapp.presentation.event.ViewEvent
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewState

sealed class StepDetailViewState(open val toolbarTitle: String) : ViewState {
    data class Idle(override val toolbarTitle: String) : StepDetailViewState(toolbarTitle)
    data class Loaded(
        val stepDescription: String,
        val videoUrl: String,
        val stepIndex: Int,
        val totalSteps: Int,
        val currentPosition: Int,
        val showPrev: Boolean,
        @StringRes val nextButtonText: Int,
        val showVideo: Boolean,
        override val toolbarTitle: String
    ) : StepDetailViewState(toolbarTitle) {
        val progressText: String
            get() = "$currentPosition/$totalSteps"
    }

    data class FinishEvent(val closeEvent: ViewEvent<Unit>) : StepDetailViewState("")
}
