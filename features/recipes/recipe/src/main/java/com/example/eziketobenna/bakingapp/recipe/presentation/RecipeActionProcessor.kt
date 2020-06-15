package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.core.ext.logE
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.usecase.FetchRecipes
import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction.LoadInitialAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction.RefreshRecipesAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction.RetryFetchAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewResult.LoadInitialResult
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewResult.RefreshRecipesResult
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewResult.RetryFetchResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class RecipeActionProcessor @Inject constructor(
    private val fetchRecipesUseCase: FetchRecipes
) : ActionProcessor<RecipeViewAction, RecipeViewResult> {

    private val recipes: Flow<List<Recipe>>
        get() = fetchRecipesUseCase()

    override fun actionToResultProcessor(viewAction: RecipeViewAction): Flow<RecipeViewResult> {
        return when (viewAction) {
            LoadInitialAction -> loadRecipes
            RetryFetchAction -> retryFetch
            RefreshRecipesAction -> refreshData
        }
    }

    private val refreshData: Flow<RecipeViewResult>
        get() = recipes.map { recipes ->
            RefreshRecipesResult.Success(recipes = recipes) as RefreshRecipesResult
        }.onStart {
            emit(RefreshRecipesResult.Refreshing)
        }.catch { cause: Throwable ->
            logE(cause)
            emit(RefreshRecipesResult.Error(cause))
        }

    private val retryFetch: Flow<RecipeViewResult>
        get() = recipes.map { recipes ->
            RetryFetchResult.Success(recipes) as RetryFetchResult
        }.onStart {
            emit(RetryFetchResult.Loading)
        }.catch { cause: Throwable ->
            logE(cause)
            emit(RetryFetchResult.Error(cause))
        }

    private val loadRecipes: Flow<RecipeViewResult>
        get() = recipes.map { recipes ->
            LoadInitialResult.Success(recipes = recipes) as LoadInitialResult
        }.onStart {
            emit(LoadInitialResult.Loading)
        }.catch { cause: Throwable ->
            logE(cause)
            emit(LoadInitialResult.Error(cause))
        }
}
