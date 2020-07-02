package com.example.eziketobenna.bakingapp.recipe.presentation.processor

import com.example.eziketobenna.bakingapp.domain.usecase.FetchRecipes
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction.LoadInitialAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewResult
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewResult.LoadInitialResult
import com.example.eziketobenna.bakingapp.recipe.presentation.executor.TestPostExecutionThread
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.RepoType
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.makeFakeRecipeRepository
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
    fun `check that LoadInitialAction returns LoadInitialResult`() = runBlockingTest {
        val result: List<RecipeViewResult> = testData(RepoType.DATA)
        assertThat(result.first()).isInstanceOf(LoadInitialResult.Loading::class.java)
        assertThat(result.last()).isInstanceOf(LoadInitialResult.Loaded::class.java)
    }

    @Test
    fun `check that LoadInitialAction returns Empty LoadInitialResult`() = runBlockingTest {
        val result: List<RecipeViewResult> = testData(RepoType.EMPTY)
        assertThat(result.first()).isInstanceOf(LoadInitialResult.Loading::class.java)
        assertThat(result.last()).isInstanceOf(LoadInitialResult.Empty::class.java)
    }

    @Test(expected = SocketTimeoutException::class)
    fun `check that LoadInitialAction returns Error LoadInitialResult`() = runBlockingTest {
        testData(RepoType.ERROR)
    }

    private suspend fun testData(type: RepoType, emissions: Int = 2): List<RecipeViewResult> {
        val fetchRecipes = FetchRecipes(makeFakeRecipeRepository(type), testPostExecutionThread)
        val recipeActionProcessor = RecipeActionProcessor(fetchRecipes)
        val flowResult: Flow<RecipeViewResult> =
            recipeActionProcessor.actionToResult(LoadInitialAction)
        return flowResult.take(emissions).toList()
    }
}
