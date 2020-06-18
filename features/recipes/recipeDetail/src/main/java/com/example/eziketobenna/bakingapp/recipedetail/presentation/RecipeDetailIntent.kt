package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ViewIntent
import com.example.eziketobenna.bakingapp.recipedetail.model.StepDetailItem
import com.example.eziketobenna.bakkingapp.model.model.RecipeModel
import com.example.eziketobenna.bakkingapp.model.model.StepModel

sealed class RecipeDetailIntent : ViewIntent {
    data class LoadRecipeDetailIntent(
        val recipe: RecipeModel
    ) : RecipeDetailIntent()

    data class OpenStepInfoViewIntent(
        val stepDetailItem: StepDetailItem,
        val index: Int,
        val steps: List<StepModel>
    ) : RecipeDetailIntent()
}
