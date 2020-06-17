package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ViewState
import com.example.eziketobenna.bakingapp.recipedetail.model.RecipeDetailModel
import com.example.eziketobenna.bakingapp.recipedetail.model.StepsInfoModel

sealed class RecipeDetailViewState : ViewState {
    object Idle : RecipeDetailViewState()
    data class Success(val model: List<RecipeDetailModel>) : RecipeDetailViewState()
    data class NavigateToStepInfo(val info: StepsInfoModel) : RecipeDetailViewState()
}
