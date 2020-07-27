package com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi

import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.model.StepModel
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewIntent
import com.example.eziketobenna.bakingapp.recipedetail.model.StepDetailItem

sealed class RecipeDetailViewIntent : ViewIntent {
    data class LoadRecipeDetailIntent(
        val recipe: RecipeModel
    ) : RecipeDetailViewIntent()

    data class OpenStepInfoViewIntent(
        val stepDetailItem: StepDetailItem,
        val steps: List<StepModel>
    ) : RecipeDetailViewIntent()
}
