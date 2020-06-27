package com.example.eziketobenna.bakingapp.recipedetail.presentation.processor

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewAction
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewAction.LoadRecipeDetailAction
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewAction.OpenStepInfoViewAction
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewResult
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewResult.OpenStepInfo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@FeatureScope
class RecipeDetailActionProcessor @Inject constructor() :
    ActionProcessor<RecipeDetailViewAction, RecipeDetailViewResult> {

    override fun actionToResult(viewAction: RecipeDetailViewAction): Flow<RecipeDetailViewResult> {
        return when (viewAction) {
            RecipeDetailViewAction.Idle -> flowOf(
                RecipeDetailViewResult.IdleResult
            )
            is LoadRecipeDetailAction -> handleLoadRecipeDetailAction(viewAction)
            is OpenStepInfoViewAction -> openStepInfoResult(viewAction)
        }
    }

    private fun openStepInfoResult(viewAction: OpenStepInfoViewAction): Flow<OpenStepInfo> =
        flowOf(OpenStepInfo(viewAction.step, viewAction.steps))

    private fun handleLoadRecipeDetailAction(
        viewAction: LoadRecipeDetailAction
    ): Flow<RecipeDetailViewResult> {
        return flowOf(
            RecipeDetailViewResult.LoadedData(
                viewAction.ingredients,
                viewAction.steps
            )
        )
    }
}
