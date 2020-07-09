package com.example.eziketobenna.bakingapp.recipe.presentation.fake

import com.example.eziketobenna.bakingapp.common_test.TestPostExecutionThread
import com.example.eziketobenna.bakingapp.domain.usecase.FetchRecipes
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.LoadInitialAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.RefreshRecipesAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.RetryFetchAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult
import com.example.eziketobenna.bakingapp.recipe.presentation.processor.RecipeActionProcessor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take

object FakeRecipeActionProcessor {

    private val testPostExecutionThread: TestPostExecutionThread = TestPostExecutionThread()

    private val recipeRepository: FakeRecipeRepository = FakeRecipeRepository()

    private val fetchRecipes = FetchRecipes(recipeRepository, testPostExecutionThread)

    private fun baseRecipeResult(
        type: ResponseType,
        action: RecipeViewAction
    ): Flow<RecipeViewResult> {
        recipeRepository.responseType = type
        return RecipeActionProcessor(fetchRecipes).actionToResult(action).take(2)
    }

    val loadInitialRecipeResultData: Flow<RecipeViewResult>
        get() = baseRecipeResult(ResponseType.DATA, LoadInitialAction)

    val loadInitialRecipeResultEmpty: Flow<RecipeViewResult>
        get() = baseRecipeResult(ResponseType.EMPTY, LoadInitialAction)

    val loadInitialRecipeResultError: Flow<RecipeViewResult>
        get() = baseRecipeResult(ResponseType.ERROR, LoadInitialAction)

    val retryRecipeResultData: Flow<RecipeViewResult>
        get() = baseRecipeResult(ResponseType.DATA, RetryFetchAction)

    val retryRecipeResultEmpty: Flow<RecipeViewResult>
        get() = baseRecipeResult(ResponseType.EMPTY, RetryFetchAction)

    val retryRecipeResultError: Flow<RecipeViewResult>
        get() = baseRecipeResult(ResponseType.ERROR, RetryFetchAction)

    val refreshRecipeResultData: Flow<RecipeViewResult>
        get() = baseRecipeResult(ResponseType.DATA, RefreshRecipesAction)

    val refreshRecipeResultEmpty: Flow<RecipeViewResult>
        get() = baseRecipeResult(ResponseType.EMPTY, RefreshRecipesAction)

    val refreshRecipeResultError: Flow<RecipeViewResult>
        get() = baseRecipeResult(ResponseType.ERROR, RefreshRecipesAction)
}
