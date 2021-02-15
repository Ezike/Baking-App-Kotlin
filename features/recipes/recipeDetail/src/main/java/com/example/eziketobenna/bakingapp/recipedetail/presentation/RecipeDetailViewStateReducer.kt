package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.presentation.event.ViewEvent
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.recipedetail.presentation.factory.RecipeDetailModelFactory
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewResult
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewResult.LoadedData
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewResult.OpenStepInfo
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewState
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
            is LoadedData -> handleLoadDataResult(previous, result)
            is OpenStepInfo -> navigateToStepInfo(previous, result)
        }
    }

    private fun navigateToStepInfo(
        oldState: RecipeDetailViewState,
        result: OpenStepInfo
    ): RecipeDetailViewState.NavigateToStepInfo {
        return RecipeDetailViewState.NavigateToStepInfo(
            oldState.toolbarTitle,
            ViewEvent(recipeDetailModelFactory.createStepInfoModel(result.step, result.steps))
        )
    }

    private fun handleLoadDataResult(
        oldState: RecipeDetailViewState,
        result: LoadedData
    ): RecipeDetailViewState {
        return RecipeDetailViewState.Success(
            oldState.toolbarTitle,
            recipeDetailModelFactory.makeRecipeDetailModelList(
                result.ingredients,
                result.steps
            )
        )
    }
}
