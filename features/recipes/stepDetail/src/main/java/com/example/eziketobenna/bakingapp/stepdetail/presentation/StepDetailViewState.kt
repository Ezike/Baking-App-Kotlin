package com.example.eziketobenna.bakingapp.stepdetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ViewState
import com.example.eziketobenna.bakingapp.stepdetail.model.StepUiModel

sealed class StepDetailViewState : ViewState {
    object Idle : StepDetailViewState()
    data class Loaded(val uiModel: StepUiModel) : StepDetailViewState()
}
