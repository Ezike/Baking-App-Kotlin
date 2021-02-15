package com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi

import com.example.eziketobenna.bakingapp.model.StepInfoModel
import com.example.eziketobenna.bakingapp.presentation.event.ViewEvent
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewState
import com.example.eziketobenna.bakingapp.recipedetail.model.RecipeDetailModel

sealed class RecipeDetailViewState(open val toolbarTitle: String) : ViewState {
    data class Init(override val toolbarTitle: String) : RecipeDetailViewState(toolbarTitle)
    data class Success(override val toolbarTitle: String, val model: List<RecipeDetailModel>) :
        RecipeDetailViewState(toolbarTitle)

    data class NavigateToStepInfo(
        override val toolbarTitle: String,
        val openStepInfoEvent: ViewEvent<StepInfoModel>
    ) : RecipeDetailViewState(toolbarTitle)
}
