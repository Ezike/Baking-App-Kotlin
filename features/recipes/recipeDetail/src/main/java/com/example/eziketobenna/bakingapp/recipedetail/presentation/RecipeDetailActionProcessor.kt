package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailAction.LoadRecipeDetailAction
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RecipeDetailActionProcessor @Inject constructor() :
    ActionProcessor<RecipeDetailAction, RecipeDetailResult> {

    override fun actionToResultProcessor(viewAction: RecipeDetailAction): Flow<RecipeDetailResult> {
        return when (viewAction) {
            RecipeDetailAction.Idle -> flowOf(RecipeDetailResult.IdleResult)
            is LoadRecipeDetailAction -> handleLoadRecipeDetailAction(viewAction)
        }
    }

    private fun handleLoadRecipeDetailAction(
        viewAction: LoadRecipeDetailAction
    ): Flow<RecipeDetailResult> {
        return flowOf(
            RecipeDetailResult.LoadedData(viewAction.ingredients, viewAction.steps)
        )
    }
}
