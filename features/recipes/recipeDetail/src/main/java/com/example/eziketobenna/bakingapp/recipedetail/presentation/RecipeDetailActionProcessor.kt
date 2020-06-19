package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailAction.LoadRecipeDetailAction
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailAction.OpenStepInfoViewAction
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailResult.OpenStepInfo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RecipeDetailActionProcessor @Inject constructor() :
    ActionProcessor<RecipeDetailAction, RecipeDetailResult> {

    override fun actionToResult(viewAction: RecipeDetailAction): Flow<RecipeDetailResult> {
        return when (viewAction) {
            RecipeDetailAction.Idle -> flowOf(RecipeDetailResult.IdleResult)
            is LoadRecipeDetailAction -> handleLoadRecipeDetailAction(viewAction)
            is OpenStepInfoViewAction -> openStepInfoResult(viewAction)
        }
    }

    private fun openStepInfoResult(viewAction: OpenStepInfoViewAction): Flow<OpenStepInfo> =
        flowOf(OpenStepInfo(viewAction.step, viewAction.index, viewAction.steps))

    private fun handleLoadRecipeDetailAction(
        viewAction: LoadRecipeDetailAction
    ): Flow<RecipeDetailResult> {
        return flowOf(
            RecipeDetailResult.LoadedData(viewAction.ingredients, viewAction.steps)
        )
    }
}
