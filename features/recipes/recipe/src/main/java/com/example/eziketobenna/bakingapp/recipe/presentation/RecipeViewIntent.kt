package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ViewIntent

sealed class RecipeViewIntent : ViewIntent {
    object LoadInitialViewIntent : RecipeViewIntent()
    object RecipeRetryViewIntent : RecipeViewIntent()
    object RecipeRefreshViewIntent : RecipeViewIntent()
}
