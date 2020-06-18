package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ViewState
import com.example.eziketobenna.bakkingapp.model.model.RecipeModel

data class RecipeViewState(
    val isLoading: Boolean,
    val isRefreshing: Boolean,
    val isDataUnavailable: Boolean,
    val error: String?,
    val recipes: List<RecipeModel>
) : ViewState {

    val errorMessage: String
        get() = error ?: ""

    val loadingState: RecipeViewState
        get() = this.copy(
            isLoading = true,
            isRefreshing = false,
            isDataUnavailable = false,
            error = null
        )

    val refreshingState: RecipeViewState
        get() = this.copy(
            isLoading = false,
            isRefreshing = true,
            isDataUnavailable = false,
            error = null
        )

    val emptyState: RecipeViewState
        get() = this.copy(
            isLoading = false,
            isRefreshing = false,
            isDataUnavailable = true,
            error = null
        )

    fun errorState(cause: String): RecipeViewState = this.copy(
        isLoading = false,
        isRefreshing = false,
        isDataUnavailable = false,
        error = cause
    )

    fun loadedState(recipes: List<RecipeModel>): RecipeViewState = this.copy(
        isLoading = false,
        isRefreshing = false,
        isDataUnavailable = false,
        error = null,
        recipes = recipes
    )

    val isError: Boolean
        get() = this.error != null

    companion object {
        val init: RecipeViewState
            get() = RecipeViewState(
                isLoading = false,
                isRefreshing = false,
                error = null,
                isDataUnavailable = false,
                recipes = emptyList()
            )
    }
}
