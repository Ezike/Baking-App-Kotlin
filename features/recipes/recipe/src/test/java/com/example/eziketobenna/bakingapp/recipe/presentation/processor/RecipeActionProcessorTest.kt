package com.example.eziketobenna.bakingapp.recipe.presentation.processor

import com.example.eziketobenna.bakingapp.common_test.TestPostExecutionThread
import com.example.eziketobenna.bakingapp.domain.usecase.FetchRecipes
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.FakeRecipeRepositoryError.Companion.ERROR_MSG
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.RepoType
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.makeFakeRecipeRepository
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.LoadInitialAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.RefreshRecipesAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.RetryFetchAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult.LoadInitialResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult.RefreshRecipesResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult.RetryFetchResult
import com.google.common.truth.Truth.assertThat
import java.net.SocketTimeoutException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class RecipeActionProcessorTest {

    private val testPostExecutionThread = TestPostExecutionThread()

    @Test
    fun `check that LoadInitialAction returns Loaded LoadInitialResult`() = runBlockingTest {
        val result: List<RecipeViewResult> = testData(LoadInitialAction, RepoType.DATA)
        assertThat(result.first()).isInstanceOf(LoadInitialResult.Loading::class.java)
        assertThat(result.last()).isInstanceOf(LoadInitialResult.Loaded::class.java)
    }

    @Test
    fun `check that LoadInitialAction returns Empty LoadInitialResult`() = runBlockingTest {
        val result: List<RecipeViewResult> = testData(LoadInitialAction, RepoType.EMPTY)
        assertThat(result.first()).isInstanceOf(LoadInitialResult.Loading::class.java)
        assertThat(result.last()).isInstanceOf(LoadInitialResult.Empty::class.java)
    }

    @Test
    fun `check that LoadInitialAction throws Error`() = runBlockingTest {
        val value: RecipeViewResult = testData(LoadInitialAction, RepoType.ERROR).last()
        assertThat(value).isInstanceOf(LoadInitialResult.Error::class.java)
        assertThat((value as LoadInitialResult.Error).cause).isInstanceOf(
            SocketTimeoutException::class.java
        )
        assertThat(value.cause.message).isEqualTo(ERROR_MSG)
    }

    @Test
    fun `check that RetryFetchAction returns Loaded RetryFetchResult`() = runBlockingTest {
        val result: List<RecipeViewResult> = testData(RetryFetchAction, RepoType.DATA)
        assertThat(result.first()).isInstanceOf(RetryFetchResult.Loading::class.java)
        assertThat(result.last()).isInstanceOf(RetryFetchResult.Loaded::class.java)
    }

    @Test
    fun `check that RetryFetchAction returns Empty RetryFetchResult`() = runBlockingTest {
        val result: List<RecipeViewResult> = testData(RetryFetchAction, RepoType.EMPTY)
        assertThat(result.first()).isInstanceOf(RetryFetchResult.Loading::class.java)
        assertThat(result.last()).isInstanceOf(RetryFetchResult.Empty::class.java)
    }

    @Test
    fun `check that RetryFetchAction throws Error`() = runBlockingTest {
        val value: RecipeViewResult = testData(RetryFetchAction, RepoType.ERROR).last()
        assertThat(value).isInstanceOf(RetryFetchResult.Error::class.java)
        assertThat((value as RetryFetchResult.Error).cause).isInstanceOf(
            SocketTimeoutException::class.java
        )
        assertThat(value.cause.message).isEqualTo(ERROR_MSG)
    }

    @Test
    fun `check that RefreshRecipesAction returns Loaded RefreshRecipesResult`() = runBlockingTest {
        val result: List<RecipeViewResult> = testData(RefreshRecipesAction, RepoType.DATA)
        assertThat(result.first()).isInstanceOf(RefreshRecipesResult.Refreshing::class.java)
        assertThat(result.last()).isInstanceOf(RefreshRecipesResult.Loaded::class.java)
    }

    @Test
    fun `check that RefreshRecipesAction returns Empty RefreshRecipesResult`() = runBlockingTest {
        val result: List<RecipeViewResult> = testData(RefreshRecipesAction, RepoType.EMPTY)
        assertThat(result.first()).isInstanceOf(RefreshRecipesResult.Refreshing::class.java)
        assertThat(result.last()).isInstanceOf(RefreshRecipesResult.Empty::class.java)
    }

    @Test
    fun `check that RefreshRecipesAction throws Error`() = runBlockingTest {
        val value: RecipeViewResult = testData(RefreshRecipesAction, RepoType.ERROR).last()
        assertThat(value).isInstanceOf(RefreshRecipesResult.Error::class.java)
        assertThat((value as RefreshRecipesResult.Error).cause).isInstanceOf(
            SocketTimeoutException::class.java
        )
        assertThat(value.cause.message).isEqualTo(ERROR_MSG)
    }

    private suspend fun testData(
        action: RecipeViewAction,
        type: RepoType,
        emissions: Int = 2
    ): List<RecipeViewResult> {
        val fetchRecipes = FetchRecipes(makeFakeRecipeRepository(type), testPostExecutionThread)
        val recipeActionProcessor = RecipeActionProcessor(fetchRecipes)
        val flowResult: Flow<RecipeViewResult> =
            recipeActionProcessor.actionToResult(action)
        return flowResult.take(emissions).toList()
    }
}
