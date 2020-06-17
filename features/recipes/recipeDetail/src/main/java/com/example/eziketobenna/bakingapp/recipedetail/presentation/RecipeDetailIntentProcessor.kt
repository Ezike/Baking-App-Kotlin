package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailAction.LoadRecipeDetailAction
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailIntent.LoadRecipeDetailIntent
import com.example.eziketobenna.bakkingapp.model.mapper.IngredientModelMapper
import com.example.eziketobenna.bakkingapp.model.mapper.StepModelMapper
import javax.inject.Inject

class RecipeDetailIntentProcessor @Inject constructor(
    private val stepModelMapper: StepModelMapper,
    private val ingredientModelMapper: IngredientModelMapper
) : IntentProcessor<RecipeDetailIntent, RecipeDetailAction> {

    override fun intentToAction(intent: RecipeDetailIntent): RecipeDetailAction {
        return when (intent) {
            is LoadRecipeDetailIntent -> loadRecipeDetailAction(intent)
        }
    }

    private fun loadRecipeDetailAction(intent: LoadRecipeDetailIntent): LoadRecipeDetailAction =
        LoadRecipeDetailAction(
            ingredientModelMapper.mapToDomainList(intent.recipe.ingredients),
            stepModelMapper.mapToDomainList(intent.recipe.steps)
        )
}
