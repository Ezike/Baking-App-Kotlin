package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.model.mapper.IngredientModelMapper
import com.example.eziketobenna.bakingapp.model.mapper.RecipeModelMapper
import com.example.eziketobenna.bakingapp.model.mapper.StepModelMapper
import com.example.eziketobenna.bakingapp.presentation.event.ViewEvent
import com.example.eziketobenna.bakingapp.recipe.presentation.data.DummyData
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.FakeActionProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.FakeRecipeRepositoryError.Companion.ERROR_MSG
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult.LoadInitialResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult.RefreshRecipesResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult.RetryFetchResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewState
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class RecipeViewStateReducerTest {

    private val recipeModelMapper = RecipeModelMapper(StepModelMapper(), IngredientModelMapper())
    private val recipeViewStateReducer = RecipeViewStateReducer(recipeModelMapper)

    @Test
    fun `check that loadingViewState is returned for Loading LoadInitialResult`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.loadInitialRecipeResultData.toList().first()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(RecipeViewState.init, result)
            assertThat(result).isInstanceOf(LoadInitialResult.Loading::class.java)
            assertThat(viewState.isLoading).isTrue()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNull()
            assertThat(viewState.recipes).isEmpty()
        }

    @Test
    fun `check that loadingViewState is returned for Loading RetryFetchResult`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.retryRecipeResultData.toList().first()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(RecipeViewState.init, result)
            assertThat(result).isInstanceOf(RetryFetchResult.Loading::class.java)
            assertThat(viewState.isLoading).isTrue()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNull()
            assertThat(viewState.recipes).isEmpty()
        }

    @Test
    fun `check that refreshingViewState is returned for Refreshing ReFreshRecipesResult`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.refreshRecipeResultData.toList().first()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(RecipeViewState.init, result)
            assertThat(result).isInstanceOf(RefreshRecipesResult.Refreshing::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isTrue()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNull()
            assertThat(viewState.recipes).isEmpty()
        }

    @Test
    fun `check that loadedViewState is returned for Loaded LoadInitialResult`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.loadInitialRecipeResultData.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(RecipeViewState.init, result)
            assertThat(result).isInstanceOf(LoadInitialResult.Loaded::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNull()
            assertRecipeHasData(viewState)
        }

    @Test
    fun `check that loadedViewState is returned for Loaded RetryFetchResult`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.retryRecipeResultData.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(RecipeViewState.init, result)
            assertThat(result).isInstanceOf(RetryFetchResult.Loaded::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNull()
            assertRecipeHasData(viewState)
        }

    @Test
    fun `check that loadedViewState is returned for Loaded RefreshRecipesResult`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.refreshRecipeResultData.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(RecipeViewState.init, result)
            assertThat(result).isInstanceOf(RefreshRecipesResult.Loaded::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNull()
            assertRecipeHasData(viewState)
        }

    @Test
    fun `check that emptyViewState is returned when LoadInitialResult is Empty`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.loadInitialRecipeResultEmpty.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(RecipeViewState.init, result)
            assertThat(result).isInstanceOf(LoadInitialResult.Empty::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isTrue()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNull()
            assertThat(viewState.recipes).isEmpty()
        }

    @Test
    fun `check that emptyViewState is returned when RetryFetchResult is Empty`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.retryRecipeResultEmpty.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(RecipeViewState.init, result)
            assertThat(result).isInstanceOf(RetryFetchResult.Empty::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isTrue()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNull()
            assertThat(viewState.recipes).isEmpty()
        }

    @Test
    fun `check that emptyViewState is returned when RefreshRecipesResult is Empty`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.refreshRecipeResultEmpty.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(RecipeViewState.init, result)
            assertThat(result).isInstanceOf(RefreshRecipesResult.Empty::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isTrue()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNull()
            assertThat(viewState.recipes).isEmpty()
        }

    @Test
    fun `check that noDataErrorState is returned when LoadInitialResult is Error`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.loadInitialRecipeResultError.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(RecipeViewState.init, result)
            assertThat(result).isInstanceOf(LoadInitialResult.Error::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNotNull()
            assertThat(viewState.error).isEqualTo(ERROR_MSG)
            assertThat(viewState.errorEvent).isNull()
            assertThat(viewState.recipes).isEmpty()
        }

    @Test
    fun `check that noDataErrorState is returned when RetryFetchResult is Error`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.retryRecipeResultError.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(RecipeViewState.init, result)
            assertThat(result).isInstanceOf(RetryFetchResult.Error::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNotNull()
            assertThat(viewState.error).isEqualTo(ERROR_MSG)
            assertThat(viewState.errorEvent).isNull()
            assertThat(viewState.recipes).isEmpty()
        }

    @Test
    fun `check that noDataErrorState is returned when RefreshRecipesResult is Error`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.refreshRecipeResultError.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(RecipeViewState.init, result)
            assertThat(result).isInstanceOf(RefreshRecipesResult.Error::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNotNull()
            assertThat(viewState.error).isEqualTo(ERROR_MSG)
            assertThat(viewState.errorEvent).isNull()
            assertThat(viewState.recipes).isEmpty()
        }

    @Test
    fun `check that dataAvailableErrorState is returned when LoadInitialResult is Error`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.loadInitialRecipeResultError.toList().last()
            val viewState: RecipeViewState = recipeViewStateReducer.reduce(
                RecipeViewState.init.loadedState(
                    DummyData.recipeModelList(recipeModelMapper)
                ), result
            )
            assertThat(result).isInstanceOf(LoadInitialResult.Error::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNotNull()
            assertThat(viewState.errorEvent).isEqualTo(ViewEvent(ERROR_MSG))
            assertRecipeHasData(viewState)
        }

    @Test
    fun `check that dataAvailableErrorState is returned when RetryFetchResult is Error`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.retryRecipeResultError.toList().last()
            val viewState: RecipeViewState = recipeViewStateReducer.reduce(
                RecipeViewState.init.loadedState(
                    DummyData.recipeModelList(recipeModelMapper)
                ), result
            )
            assertThat(result).isInstanceOf(RetryFetchResult.Error::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNotNull()
            assertThat(viewState.errorEvent).isEqualTo(ViewEvent(ERROR_MSG))
            assertRecipeHasData(viewState)
        }

    @Test
    fun `check that dataAvailableErrorState is returned when RefreshRecipesResult is Error`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.refreshRecipeResultError.toList().last()
            val viewState: RecipeViewState = recipeViewStateReducer.reduce(
                RecipeViewState.init.loadedState(
                    DummyData.recipeModelList(recipeModelMapper)
                ), result
            )
            assertThat(result).isInstanceOf(RefreshRecipesResult.Error::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNotNull()
            assertThat(viewState.errorEvent).isEqualTo(ViewEvent(ERROR_MSG))
            assertRecipeHasData(viewState)
        }

    @Test
    fun `check that loadedViewState is returned when LoadInitialResult is Empty and recipes are loaded`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.loadInitialRecipeResultEmpty.toList().last()
            val viewState: RecipeViewState = recipeViewStateReducer.reduce(
                RecipeViewState.init.loadedState(
                    recipes = DummyData.recipeModelList(recipeModelMapper)
                ), result
            )
            assertThat(result).isInstanceOf(LoadInitialResult.Empty::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNull()
            assertRecipeHasData(viewState)
        }

    @Test
    fun `check that loadedViewState is returned when RetryFetchResult is Empty and recipes are loaded`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.retryRecipeResultEmpty.toList().last()
            val viewState: RecipeViewState = recipeViewStateReducer.reduce(
                RecipeViewState.init.loadedState(
                    recipes = DummyData.recipeModelList(recipeModelMapper)
                ), result
            )
            assertThat(result).isInstanceOf(RetryFetchResult.Empty::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNull()
            assertRecipeHasData(viewState)
        }

    @Test
    fun `check that loadedViewState is returned when RefreshRecipesResult is Empty and recipes are loaded`() =
        runBlockingTest {
            val result: RecipeViewResult =
                FakeActionProcessor.refreshRecipeResultEmpty.toList().last()
            val viewState: RecipeViewState = recipeViewStateReducer.reduce(
                RecipeViewState.init.loadedState(
                    recipes = DummyData.recipeModelList(recipeModelMapper)
                ), result
            )
            assertThat(result).isInstanceOf(RefreshRecipesResult.Empty::class.java)
            assertThat(viewState.isLoading).isFalse()
            assertThat(viewState.isRefreshing).isFalse()
            assertThat(viewState.isDataUnavailable).isFalse()
            assertThat(viewState.error).isNull()
            assertThat(viewState.errorEvent).isNull()
            assertRecipeHasData(viewState)
        }

    private fun assertRecipeHasData(viewState: RecipeViewState) {
        assertThat(viewState.recipes).isNotEmpty()
        assertThat(viewState.recipes).isEqualTo(DummyData.recipeModelList(recipeModelMapper))
    }
}
