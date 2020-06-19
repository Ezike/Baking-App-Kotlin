package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.presentation.event.ViewEvent
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewState
import com.example.eziketobenna.bakkingapp.model.model.RecipeModel

data class RecipeViewState(
    val isLoading: Boolean,
    val isRefreshing: Boolean,
    val isDataUnavailable: Boolean,
    val error: String?,
    val errorEvent: ViewEvent<String>?,
    val recipes: List<RecipeModel>
) : ViewState {

    val loadingState: RecipeViewState
        get() = this.copy(
            isLoading = true,
            isRefreshing = false,
            isDataUnavailable = false,
            error = null,
            errorEvent = null
        )

    val refreshingState: RecipeViewState
        get() = this.copy(
            isLoading = false,
            isRefreshing = true,
            isDataUnavailable = false,
            error = null,
            errorEvent = null
        )

    val emptyState: RecipeViewState
        get() = this.copy(
            isLoading = false,
            isRefreshing = false,
            isDataUnavailable = true,
            error = null,
            errorEvent = null
        )

    fun noDataErrorState(cause: String): RecipeViewState = this.copy(
        isLoading = false,
        isRefreshing = false,
        isDataUnavailable = false,
        error = cause,
        errorEvent = null
    )

    fun dataAvailableErrorState(cause: String): RecipeViewState = this.copy(
        isLoading = false,
        isRefreshing = false,
        isDataUnavailable = false,
        error = null,
        errorEvent = ViewEvent(cause)
    )

    fun loadedState(recipes: List<RecipeModel>): RecipeViewState = this.copy(
        isLoading = false,
        isRefreshing = false,
        isDataUnavailable = false,
        error = null,
        errorEvent = null,
        recipes = recipes
    )

    val isNoDataError: Boolean
        get() = this.error != null

    val isDataAvailableError: Boolean
        get() = this.errorEvent != null

    companion object {
        val init: RecipeViewState
            get() = RecipeViewState(
                isLoading = false,
                isRefreshing = false,
                error = null,
                isDataUnavailable = false,
                errorEvent = null,
                recipes = emptyList()
            )
    }
}
