package com.example.eziketobenna.bakingapp.recipe.presentation.processor

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.usecase.FetchRecipes
import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction.LoadInitialAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction.RefreshRecipesAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction.RetryFetchAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewResult
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewResult.LoadInitialResult
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewResult.RefreshRecipesResult
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewResult.RetryFetchResult
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@FeatureScope
class RecipeActionProcessor @Inject constructor(
    private val fetchRecipesUseCase: FetchRecipes
) : ActionProcessor<RecipeViewAction, RecipeViewResult> {

    private val recipes: Flow<List<Recipe>>
        get() = fetchRecipesUseCase()

    override fun actionToResult(viewAction: RecipeViewAction): Flow<RecipeViewResult> {
        return when (viewAction) {
            LoadInitialAction -> loadRecipes
            RetryFetchAction -> retryFetch
            RefreshRecipesAction -> refreshData
        }
    }

    private val refreshData: Flow<RecipeViewResult>
        get() = recipes.map { recipes ->
            if (recipes.isNotEmpty()) {
                RefreshRecipesResult.Loaded(recipes = recipes)
            } else {
                RefreshRecipesResult.Empty
            }
        }.onStart {
            emit(RefreshRecipesResult.Refreshing)
        }.catch { cause: Throwable ->
            emit(RefreshRecipesResult.Error(cause))
        }

    private val retryFetch: Flow<RecipeViewResult>
        get() = recipes.map { recipes ->
            if (recipes.isNotEmpty()) {
                RetryFetchResult.Loaded(recipes)
            } else {
                RetryFetchResult.Empty
            }
        }.onStart {
            emit(RetryFetchResult.Loading)
        }.catch { cause: Throwable ->
            emit(RetryFetchResult.Error(cause))
        }

    private val loadRecipes: Flow<RecipeViewResult>
        get() = recipes.map { recipes ->
            if (recipes.isNotEmpty()) {
                LoadInitialResult.Loaded(recipes = recipes)
            } else {
                LoadInitialResult.Empty
            }
        }.onStart {
            emit(LoadInitialResult.Loading)
        }.catch { cause: Throwable ->
            emit(LoadInitialResult.Error(cause))
        }
}
