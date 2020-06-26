package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.model.mapper.IngredientModelMapper
import com.example.eziketobenna.bakingapp.model.mapper.StepModelMapper
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.recipedetail.model.StepDetailMapper
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewAction.LoadRecipeDetailAction
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewAction.OpenStepInfoViewAction
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewIntent.LoadRecipeDetailIntent
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewIntent.OpenStepInfoViewIntent
import javax.inject.Inject

@FeatureScope
class RecipeDetailIntentProcessor @Inject constructor(
    private val stepModelMapper: StepModelMapper,
    private val ingredientModelMapper: IngredientModelMapper,
    private val stepDetailMapper: StepDetailMapper
) : IntentProcessor<RecipeDetailViewIntent, RecipeDetailViewAction> {

    override fun intentToAction(intent: RecipeDetailViewIntent): RecipeDetailViewAction {
        return when (intent) {
            is LoadRecipeDetailIntent -> loadRecipeDetailAction(intent)
            is OpenStepInfoViewIntent -> openStepInfoViewAction(intent)
        }
    }

    private fun openStepInfoViewAction(intent: OpenStepInfoViewIntent): OpenStepInfoViewAction {
        return OpenStepInfoViewAction(
            step = stepDetailMapper.mapToDomain(intent.stepDetailItem),
            steps = stepModelMapper.mapToDomainList(intent.steps)
        )
    }

    private fun loadRecipeDetailAction(intent: LoadRecipeDetailIntent): LoadRecipeDetailAction =
        LoadRecipeDetailAction(
            ingredientModelMapper.mapToDomainList(intent.recipe.ingredients),
            stepModelMapper.mapToDomainList(intent.recipe.steps)
        )
}
