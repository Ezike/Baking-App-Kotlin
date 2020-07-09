package com.example.eziketobenna.bakingapp.recipe.presentation.processor

import com.example.eziketobenna.bakingapp.common_test.FlowRecorder
import com.example.eziketobenna.bakingapp.common_test.TestPostExecutionThread
import com.example.eziketobenna.bakingapp.common_test.recordWith
import com.example.eziketobenna.bakingapp.domain.usecase.FetchRecipes
import com.example.eziketobenna.bakingapp.recipe.presentation.data.DummyData
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.FakeRecipeRepository
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.FakeRecipeRepository.Companion.ERROR_MSG
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.ResponseType
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.LoadInitialAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.RefreshRecipesAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.RetryFetchAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult.LoadInitialResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult.RefreshRecipesResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult.RetryFetchResult
import com.google.common.truth.IterableSubject
import com.google.common.truth.Truth.assertThat
import java.net.SocketTimeoutException
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class RecipeActionProcessorTest {

    private val testPostExecutionThread = TestPostExecutionThread()

    private val fakeRecipeRepository = FakeRecipeRepository()

    private val fetchRecipes = FetchRecipes(fakeRecipeRepository, testPostExecutionThread)

    private val recipeActionProcessor = RecipeActionProcessor(fetchRecipes)

    private val resultRecorder: FlowRecorder<RecipeViewResult> = FlowRecorder(TestCoroutineScope())

    @Test
    fun `check that LoadInitialAction returns Loaded LoadInitialResult`() = runBlockingTest {
        recordResult(LoadInitialAction, ResponseType.DATA)
        assertThat(resultRecorder.takeAll())
            .contains(LoadInitialResult.Loading, LoadInitialResult.Loaded(DummyData.recipeList))
    }

    @Test
    fun `check that LoadInitialAction returns Empty LoadInitialResult`() = runBlockingTest {
        recordResult(LoadInitialAction, ResponseType.EMPTY)
        assertThat(resultRecorder.takeAll())
            .contains(LoadInitialResult.Loading, LoadInitialResult.Empty)
    }

    @Test
    fun `check that LoadInitialAction throws Error`() = runBlockingTest {
        recordResult(LoadInitialAction, ResponseType.ERROR)
        val results: List<RecipeViewResult> = resultRecorder.takeAll()
        assertThat(results.map { it.javaClass })
            .contains(
                LoadInitialResult.Loading::class.java,
                LoadInitialResult.Error::class.java
            )
        val errorResult: LoadInitialResult.Error = results.last() as LoadInitialResult.Error
        assertThat(errorResult.cause).isInstanceOf(SocketTimeoutException::class.java)
        assertThat(errorResult.cause.message).isEqualTo(ERROR_MSG)
    }

    @Test
    fun `check that RetryFetchAction returns Loaded RetryFetchResult`() = runBlockingTest {
        recordResult(RetryFetchAction, ResponseType.DATA)
        assertThat(resultRecorder.takeAll()).contains(
            RetryFetchResult.Loading,
            RetryFetchResult.Loaded(DummyData.recipeList)
        )
    }

    @Test
    fun `check that RetryFetchAction returns Empty RetryFetchResult`() = runBlockingTest {
        recordResult(RetryFetchAction, ResponseType.EMPTY)
        assertThat(resultRecorder.takeAll()).contains(
            RetryFetchResult.Loading,
            RetryFetchResult.Empty
        )
    }

    @Test
    fun `check that RetryFetchAction throws Error`() = runBlockingTest {
        recordResult(RetryFetchAction, ResponseType.ERROR)
        val results: List<RecipeViewResult> = resultRecorder.takeAll()
        assertThat(results.map { it.javaClass })
            .contains(
                RetryFetchResult.Loading::class.java,
                RetryFetchResult.Error::class.java
            )
        val errorResult: RetryFetchResult.Error = results.last() as RetryFetchResult.Error
        assertThat(errorResult.cause).isInstanceOf(SocketTimeoutException::class.java)
        assertThat(errorResult.cause.message).isEqualTo(ERROR_MSG)
    }

    @Test
    fun `check that RefreshRecipesAction returns Loaded RefreshRecipesResult`() = runBlockingTest {
        recordResult(RefreshRecipesAction, ResponseType.DATA)
        assertThat(resultRecorder.takeAll()).contains(
            RefreshRecipesResult.Refreshing,
            RefreshRecipesResult.Loaded(DummyData.recipeList)
        )
    }

    @Test
    fun `check that RefreshRecipesAction returns Empty RefreshRecipesResult`() = runBlockingTest {
        recordResult(RefreshRecipesAction, ResponseType.EMPTY)
        assertThat(resultRecorder.takeAll()).contains(
            RefreshRecipesResult.Refreshing,
            RefreshRecipesResult.Empty
        )
    }

    @Test
    fun `check that RefreshRecipesAction throws Error`() = runBlockingTest {
        recordResult(RefreshRecipesAction, ResponseType.ERROR)
        val results: List<RecipeViewResult> = resultRecorder.takeAll()
        assertThat(results.map { it.javaClass })
            .contains(
                RefreshRecipesResult.Refreshing::class.java,
                RefreshRecipesResult.Error::class.java
            )
        val errorResult: RefreshRecipesResult.Error = results.last() as RefreshRecipesResult.Error
        assertThat(errorResult.cause).isInstanceOf(SocketTimeoutException::class.java)
        assertThat(errorResult.cause.message).isEqualTo(ERROR_MSG)
    }

    private fun recordResult(action: RecipeViewAction, type: ResponseType) {
        fakeRecipeRepository.responseType = type
        recipeActionProcessor.actionToResult(action).recordWith(resultRecorder)
    }

    private inline fun <reified T> IterableSubject.contains(vararg instance: T) {
        containsExactlyElementsIn(instance).inOrder()
    }
}
