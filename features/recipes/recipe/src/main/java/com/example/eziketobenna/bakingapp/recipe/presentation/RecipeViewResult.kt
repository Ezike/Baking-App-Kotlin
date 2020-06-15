package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewResult

sealed class RecipeViewResult : ViewResult {

    sealed class LoadInitialResult : RecipeViewResult() {
        data class Success(val recipes: List<Recipe>) : LoadInitialResult()
        object Loading : LoadInitialResult()
        data class Error(val cause: Throwable) : LoadInitialResult()
    }

    sealed class RetryFetchResult : RecipeViewResult() {
        data class Success(val recipes: List<Recipe>) : RetryFetchResult()
        object Loading : RetryFetchResult()
        data class Error(val cause: Throwable) : RetryFetchResult()
    }

    sealed class RefreshRecipesResult : RecipeViewResult() {
        data class Success(val recipes: List<Recipe>) : RefreshRecipesResult()
        object Refreshing : RefreshRecipesResult()
        data class Error(val cause: Throwable) : RefreshRecipesResult()
    }
}
