package com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi

import com.example.eziketobenna.bakingapp.model.StepInfoModel
import com.example.eziketobenna.bakingapp.model.StepModel
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewIntent

sealed class StepDetailViewIntent : ViewIntent {
    data class LoadInitialViewIntent(val stepInfoModel: StepInfoModel) : StepDetailViewIntent()
    data class GoToNextStepViewIntent(val steps: List<StepModel>) : StepDetailViewIntent()
    data class GoToPreviousStepViewIntent(val steps: List<StepModel>) : StepDetailViewIntent()
}
