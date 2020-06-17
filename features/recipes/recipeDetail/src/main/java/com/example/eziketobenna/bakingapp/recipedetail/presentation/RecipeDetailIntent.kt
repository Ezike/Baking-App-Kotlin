package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ViewIntent
import com.example.eziketobenna.bakkingapp.model.model.RecipeModel

sealed class RecipeDetailIntent : ViewIntent {
    data class LoadRecipeDetailIntent(val recipe: RecipeModel) : RecipeDetailIntent()
}
