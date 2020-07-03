package com.example.eziketobenna.bakingapp.recipe.presentation.mvi

import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewResult

sealed class RecipeViewResult : ViewResult {

    sealed class LoadInitialResult : RecipeViewResult() {
        data class Loaded(val recipes: List<Recipe>) : LoadInitialResult()
        object Loading : LoadInitialResult()
        object Empty : LoadInitialResult()
        data class Error(val cause: Throwable) : LoadInitialResult()
    }

    sealed class RetryFetchResult : RecipeViewResult() {
        data class Loaded(val recipes: List<Recipe>) : RetryFetchResult()
        object Loading : RetryFetchResult()
        object Empty : RetryFetchResult()
        data class Error(val cause: Throwable) : RetryFetchResult()
    }

    sealed class RefreshRecipesResult : RecipeViewResult() {
        data class Loaded(val recipes: List<Recipe>) : RefreshRecipesResult()
        object Refreshing : RefreshRecipesResult()
        object Empty : RefreshRecipesResult()
        data class Error(val cause: Throwable) : RefreshRecipesResult()
    }
}
