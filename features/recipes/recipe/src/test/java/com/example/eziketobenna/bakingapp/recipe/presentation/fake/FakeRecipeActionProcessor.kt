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

    private fun baseRecipeResult(
        type: RepoType,
        action: RecipeViewAction,
        testPostExecutionThread: TestPostExecutionThread = TestPostExecutionThread()
    ): Flow<RecipeViewResult> {
        val fetchRecipes = FetchRecipes(makeFakeRecipeRepository(type), testPostExecutionThread)
        val recipeActionProcessor = RecipeActionProcessor(fetchRecipes)
        return recipeActionProcessor.actionToResult(action).take(2)
    }

    val loadInitialRecipeResultData: Flow<RecipeViewResult>
        get() = baseRecipeResult(RepoType.DATA, LoadInitialAction)

    val loadInitialRecipeResultEmpty: Flow<RecipeViewResult>
        get() = baseRecipeResult(RepoType.EMPTY, LoadInitialAction)

    val loadInitialRecipeResultError: Flow<RecipeViewResult>
        get() = baseRecipeResult(RepoType.ERROR, LoadInitialAction)

    val retryRecipeResultData: Flow<RecipeViewResult>
        get() = baseRecipeResult(RepoType.DATA, RetryFetchAction)

    val retryRecipeResultEmpty: Flow<RecipeViewResult>
        get() = baseRecipeResult(RepoType.EMPTY, RetryFetchAction)

    val retryRecipeResultError: Flow<RecipeViewResult>
        get() = baseRecipeResult(RepoType.ERROR, RetryFetchAction)

    val refreshRecipeResultData: Flow<RecipeViewResult>
        get() = baseRecipeResult(RepoType.DATA, RefreshRecipesAction)

    val refreshRecipeResultEmpty: Flow<RecipeViewResult>
        get() = baseRecipeResult(RepoType.EMPTY, RefreshRecipesAction)

    val refreshRecipeResultError: Flow<RecipeViewResult>
        get() = baseRecipeResult(RepoType.ERROR, RefreshRecipesAction)
}
