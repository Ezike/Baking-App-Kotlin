package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.model.StepModel
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewIntent
import com.example.eziketobenna.bakingapp.recipedetail.model.StepDetailItem

sealed class RecipeDetailIntent : ViewIntent {
    data class LoadRecipeDetailIntent(
        val recipe: RecipeModel
    ) : RecipeDetailIntent()

    data class OpenStepInfoViewIntent(
        val stepDetailItem: StepDetailItem,
        val steps: List<StepModel>
    ) : RecipeDetailIntent()
}
