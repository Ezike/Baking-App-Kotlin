package com.example.eziketobenna.bakingapp.stepdetail.presentation

import com.example.eziketobenna.bakingapp.model.StepInfoModel
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewIntent

sealed class StepDetailViewIntent : ViewIntent {
    data class LoadInitialViewIntent(val stepInfoModel: StepInfoModel) : StepDetailViewIntent()
}
