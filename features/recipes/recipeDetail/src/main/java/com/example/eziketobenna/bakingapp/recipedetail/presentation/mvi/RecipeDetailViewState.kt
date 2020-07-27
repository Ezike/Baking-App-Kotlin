package com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi

import com.example.eziketobenna.bakingapp.model.StepInfoModel
import com.example.eziketobenna.bakingapp.presentation.event.ViewEvent
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewState
import com.example.eziketobenna.bakingapp.recipedetail.model.RecipeDetailModel

sealed class RecipeDetailViewState : ViewState {
    object Idle : RecipeDetailViewState()
    data class Success(val model: List<RecipeDetailModel>) : RecipeDetailViewState()
    data class NavigateToStepInfo(val openStepInfoEvent: ViewEvent<StepInfoModel>) : RecipeDetailViewState()
}
