package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.core.ext.errorMessage
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakkingapp.model.mapper.RecipeModelMapper
import javax.inject.Inject

@FeatureScope
class RecipeViewStateReducer @Inject constructor(private val recipeModelMapper: RecipeModelMapper) :
    ViewStateReducer<RecipeViewState, RecipeViewResult> {

    override fun reduce(previous: RecipeViewState, result: RecipeViewResult): RecipeViewState {
        return when (result) {
            is RecipeViewResult.LoadInitialResult -> handleLoadInitialResult(
                recipeModelMapper,
                result
            )
            is RecipeViewResult.RetryFetchResult -> handleRetryFetchResult(
                recipeModelMapper,
                result
            )
            is RecipeViewResult.RefreshRecipesResult -> handleRefreshRecipeResult(
                recipeModelMapper,
                result
            )
        }
    }

    private fun handleLoadInitialResult(
        mapper: RecipeModelMapper,
        loadInitialResult: RecipeViewResult.LoadInitialResult
    ): RecipeViewState {
        return when (loadInitialResult) {
            is RecipeViewResult.LoadInitialResult.Success -> {
                RecipeViewState.Success(mapper.mapToModelList(loadInitialResult.recipes))
            }
            RecipeViewResult.LoadInitialResult.Loading -> RecipeViewState.Loading
            is RecipeViewResult.LoadInitialResult.Error ->
                RecipeViewState.Error(loadInitialResult.cause.errorMessage)
        }
    }

    private fun handleRetryFetchResult(
        mapper: RecipeModelMapper,
        retryFetchResult: RecipeViewResult.RetryFetchResult
    ): RecipeViewState {
        return when (retryFetchResult) {
            is RecipeViewResult.RetryFetchResult.Success -> {
                RecipeViewState.Success(mapper.mapToModelList(retryFetchResult.recipes))
            }
            RecipeViewResult.RetryFetchResult.Loading -> RecipeViewState.Loading
            is RecipeViewResult.RetryFetchResult.Error ->
                RecipeViewState.Error(retryFetchResult.cause.errorMessage)
        }
    }

    private fun handleRefreshRecipeResult(
        mapper: RecipeModelMapper,
        refreshRecipesResult: RecipeViewResult.RefreshRecipesResult
    ): RecipeViewState {
        return when (refreshRecipesResult) {
            is RecipeViewResult.RefreshRecipesResult.Success -> {
                RecipeViewState.Success(mapper.mapToModelList(refreshRecipesResult.recipes))
            }
            RecipeViewResult.RefreshRecipesResult.Refreshing -> RecipeViewState.Refreshing
            is RecipeViewResult.RefreshRecipesResult.Error -> RecipeViewState.Error(
                refreshRecipesResult.cause.errorMessage
            )
        }
    }
}
