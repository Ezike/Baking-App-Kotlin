package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.core.ext.errorMessage
import com.example.eziketobenna.bakingapp.model.mapper.RecipeModelMapper
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult.LoadInitialResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult.RefreshRecipesResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult.RetryFetchResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewState
import javax.inject.Inject

@FeatureScope
class RecipeViewStateReducer @Inject constructor(private val recipeModelMapper: RecipeModelMapper) :
    ViewStateReducer<RecipeViewState, RecipeViewResult> {

    override fun reduce(previous: RecipeViewState, result: RecipeViewResult): RecipeViewState {
        return when (result) {
            is LoadInitialResult -> handleLoadInitialResult(
                recipeModelMapper,
                result,
                previous
            )
            is RetryFetchResult -> handleRetryFetchResult(
                recipeModelMapper,
                result,
                previous
            )
            is RefreshRecipesResult -> handleRefreshRecipeResult(
                recipeModelMapper,
                result,
                previous
            )
        }
    }

    private fun handleEmptyState(previous: RecipeViewState): RecipeViewState {
        return if (previous.recipes.isEmpty()) previous.emptyState
        else previous.loadedState(previous.recipes)
    }

    private fun handleErrorState(previous: RecipeViewState, cause: String): RecipeViewState {
        return if (previous.recipes.isEmpty()) previous.noDataErrorState(cause)
        else previous.dataAvailableErrorState(cause)
    }

    private fun handleLoadInitialResult(
        mapper: RecipeModelMapper,
        loadInitialResult: LoadInitialResult,
        previous: RecipeViewState
    ): RecipeViewState {
        return when (loadInitialResult) {
            is LoadInitialResult.Loaded -> previous.loadedState(
                mapper.mapToModelList(
                    loadInitialResult.recipes
                )
            )
            is LoadInitialResult.Error -> handleErrorState(
                previous,
                loadInitialResult.cause.errorMessage
            )
            LoadInitialResult.Empty -> handleEmptyState(previous)
            LoadInitialResult.Loading -> previous.loadingState
        }
    }

    private fun handleRetryFetchResult(
        mapper: RecipeModelMapper,
        retryFetchResult: RetryFetchResult,
        previous: RecipeViewState
    ): RecipeViewState {
        return when (retryFetchResult) {
            is RetryFetchResult.Loaded -> previous.loadedState(
                mapper.mapToModelList(
                    retryFetchResult.recipes
                )
            )
            is RetryFetchResult.Error -> handleErrorState(
                previous,
                retryFetchResult.cause.errorMessage
            )
            RetryFetchResult.Loading -> previous.loadingState
            RetryFetchResult.Empty -> handleEmptyState(previous)
        }
    }

    private fun handleRefreshRecipeResult(
        mapper: RecipeModelMapper,
        refreshRecipesResult: RefreshRecipesResult,
        previous: RecipeViewState
    ): RecipeViewState {
        return when (refreshRecipesResult) {
            is RefreshRecipesResult.Loaded -> previous.loadedState(
                mapper.mapToModelList(
                    refreshRecipesResult.recipes
                )
            )

            is RefreshRecipesResult.Error -> handleErrorState(
                previous,
                refreshRecipesResult.cause.errorMessage
            )
            RefreshRecipesResult.Refreshing -> previous.refreshingState
            RefreshRecipesResult.Empty -> handleEmptyState(previous)
        }
    }
}
