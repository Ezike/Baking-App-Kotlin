package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ViewIntent

sealed class RecipeViewIntent : ViewIntent {
    object LoadInitial : RecipeViewIntent()
    object Retry : RecipeViewIntent()
    object Refresh : RecipeViewIntent()
}
