package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.presentation.event.ViewEvent
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewResult.LoadedData
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewResult.OpenStepInfo
import com.example.eziketobenna.bakingapp.recipedetail.presentation.factory.RecipeDetailModelFactory
import javax.inject.Inject

@FeatureScope
class RecipeDetailViewStateReducer @Inject constructor(
    private val recipeDetailModelFactory: RecipeDetailModelFactory
) : ViewStateReducer<RecipeDetailViewState, RecipeDetailViewResult> {

    override fun reduce(
        previous: RecipeDetailViewState,
        result: RecipeDetailViewResult
    ): RecipeDetailViewState {

        return when (result) {
            RecipeDetailViewResult.IdleResult -> RecipeDetailViewState.Idle
            is LoadedData -> handleLoadDataResult(result)
            is OpenStepInfo -> navigateToStepInfo(result)
        }
    }

    private fun navigateToStepInfo(result: OpenStepInfo): RecipeDetailViewState.NavigateToStepInfo {
        return RecipeDetailViewState.NavigateToStepInfo(
            ViewEvent(recipeDetailModelFactory.createStepInfoModel(result.step, result.steps))
        )
    }

    private fun handleLoadDataResult(result: LoadedData): RecipeDetailViewState {
        return RecipeDetailViewState.Success(
            recipeDetailModelFactory.makeRecipeDetailModelList(result.ingredients, result.steps)
        )
    }
}
