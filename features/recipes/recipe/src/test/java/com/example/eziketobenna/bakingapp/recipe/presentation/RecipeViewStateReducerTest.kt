package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.model.mapper.IngredientModelMapper
import com.example.eziketobenna.bakingapp.model.mapper.RecipeModelMapper
import com.example.eziketobenna.bakingapp.model.mapper.StepModelMapper
import com.example.eziketobenna.bakingapp.recipe.presentation.data.DummyData
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.FakeRecipeActionProcessor
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
    fun `check that loadingViewState is returned when LoadInitialResult is Loading`() {
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.loadInitialRecipeResultData.toList().first()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(LoadInitialResult.Loading::class.java)
            assertThat(viewState).isEqualTo(initialState.loadingState)
        }
    }

    @Test
    fun `check that loadingViewState is returned when RetryFetchResult is Loading`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.retryRecipeResultData.toList().first()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(RetryFetchResult.Loading::class.java)
            assertThat(viewState).isEqualTo(initialState.loadingState)
        }

    @Test
    fun `check that refreshingViewState is returned when ReFreshRecipesResult is Refreshing`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.refreshRecipeResultData.toList().first()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(RefreshRecipesResult.Refreshing::class.java)
            assertThat(viewState).isEqualTo(initialState.refreshingState)
        }

    @Test
    fun `check that loadedViewState is returned when LoadInitialResult is Loaded`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.loadInitialRecipeResultData.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(LoadInitialResult.Loaded::class.java)
            assertThat(viewState).isEqualTo(
                initialState.loadedState(DummyData.recipeModelList(recipeModelMapper))
            )
        }

    @Test
    fun `check that loadedViewState is returned when RetryFetchResult is Loaded`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.retryRecipeResultData.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(RetryFetchResult.Loaded::class.java)
            assertThat(viewState).isEqualTo(
                initialState.loadedState(DummyData.recipeModelList(recipeModelMapper))
            )
        }

    @Test
    fun `check that loadedViewState is returned when RefreshRecipesResult is Loaded`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.refreshRecipeResultData.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(RefreshRecipesResult.Loaded::class.java)
            assertThat(viewState).isEqualTo(
                initialState.loadedState(DummyData.recipeModelList(recipeModelMapper))
            )
        }

    @Test
    fun `check that emptyViewState is returned when LoadInitialResult is Empty`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.loadInitialRecipeResultEmpty.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(LoadInitialResult.Empty::class.java)
            assertThat(viewState).isEqualTo(initialState.emptyState)
        }

    @Test
    fun `check that emptyViewState is returned when RetryFetchResult is Empty`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.retryRecipeResultEmpty.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(RetryFetchResult.Empty::class.java)
            assertThat(viewState).isEqualTo(initialState.emptyState)
        }

    @Test
    fun `check that emptyViewState is returned when RefreshRecipesResult is Empty`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.refreshRecipeResultEmpty.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(RefreshRecipesResult.Empty::class.java)
            assertThat(viewState).isEqualTo(initialState.emptyState)
        }

    @Test
    fun `check that noDataErrorState is returned when LoadInitialResult is Error and recipes is Empty`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.loadInitialRecipeResultError.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(LoadInitialResult.Error::class.java)
            assertThat(viewState).isEqualTo(initialState.noDataErrorState(ERROR_MSG))
        }

    @Test
    fun `check that noDataErrorState is returned when RetryFetchResult is Error and recipes is Empty`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.retryRecipeResultError.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(RetryFetchResult.Error::class.java)
            assertThat(viewState).isEqualTo(initialState.noDataErrorState(ERROR_MSG))
        }

    @Test
    fun `check that noDataErrorState is returned when RefreshRecipesResult is Error and recipes is Empty`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.refreshRecipeResultError.toList().last()
            val viewState: RecipeViewState =
                recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(RefreshRecipesResult.Error::class.java)
            assertThat(viewState).isEqualTo(initialState.noDataErrorState(ERROR_MSG))
        }

    @Test
    fun `check that dataAvailableErrorState is returned when LoadInitialResult is Error and recipes is not Empty`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init.loadedState(
                DummyData.recipeModelList(recipeModelMapper)
            )
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.loadInitialRecipeResultError.toList().last()
            val viewState: RecipeViewState = recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(LoadInitialResult.Error::class.java)
            assertThat(viewState).isEqualTo(initialState.dataAvailableErrorState(ERROR_MSG))
        }

    @Test
    fun `check that dataAvailableErrorState is returned when RetryFetchResult is Error and recipes is not Empty`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init.loadedState(
                DummyData.recipeModelList(recipeModelMapper)
            )
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.retryRecipeResultError.toList().last()
            val viewState: RecipeViewState = recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(RetryFetchResult.Error::class.java)
            assertThat(viewState).isEqualTo(initialState.dataAvailableErrorState(ERROR_MSG))
        }

    @Test
    fun `check that dataAvailableErrorState is returned when RefreshRecipesResult is Error and recipes is not Empty`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init.loadedState(
                DummyData.recipeModelList(recipeModelMapper)
            )
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.refreshRecipeResultError.toList().last()
            val viewState: RecipeViewState = recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(RefreshRecipesResult.Error::class.java)
            assertThat(viewState).isEqualTo(initialState.dataAvailableErrorState(ERROR_MSG))
        }

    @Test
    fun `check that loadedViewState is returned when LoadInitialResult is Empty and recipes are loaded`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init.loadedState(
                DummyData.recipeModelList(recipeModelMapper)
            )
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.loadInitialRecipeResultEmpty.toList().last()
            val viewState: RecipeViewState = recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(LoadInitialResult.Empty::class.java)
            assertThat(viewState).isEqualTo(
                initialState.loadedState(DummyData.recipeModelList(recipeModelMapper))
            )
        }

    @Test
    fun `check that loadedViewState is returned when RetryFetchResult is Empty and recipes are loaded`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init.loadedState(
                DummyData.recipeModelList(recipeModelMapper)
            )
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.retryRecipeResultEmpty.toList().last()
            val viewState: RecipeViewState = recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(RetryFetchResult.Empty::class.java)
            assertThat(viewState).isEqualTo(
                initialState.loadedState(DummyData.recipeModelList(recipeModelMapper))
            )
        }

    @Test
    fun `check that loadedViewState is returned when RefreshRecipesResult is Empty and recipes are loaded`() =
        runBlockingTest {
            val initialState: RecipeViewState = RecipeViewState.init.loadedState(
                DummyData.recipeModelList(recipeModelMapper)
            )
            val result: RecipeViewResult =
                FakeRecipeActionProcessor.refreshRecipeResultEmpty.toList().last()
            val viewState: RecipeViewState = recipeViewStateReducer.reduce(initialState, result)
            assertThat(result).isInstanceOf(RefreshRecipesResult.Empty::class.java)
            assertThat(viewState).isEqualTo(
                initialState.loadedState(DummyData.recipeModelList(recipeModelMapper))
            )
        }
}
