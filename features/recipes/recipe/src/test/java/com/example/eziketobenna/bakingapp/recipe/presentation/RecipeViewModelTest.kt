package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.common_test.FlowRecorder
import com.example.eziketobenna.bakingapp.common_test.MainCoroutineRule
import com.example.eziketobenna.bakingapp.common_test.TestPostExecutionThread
import com.example.eziketobenna.bakingapp.common_test.recordWith
import com.example.eziketobenna.bakingapp.domain.usecase.FetchRecipes
import com.example.eziketobenna.bakingapp.model.mapper.IngredientModelMapper
import com.example.eziketobenna.bakingapp.model.mapper.RecipeModelMapper
import com.example.eziketobenna.bakingapp.model.mapper.StepModelMapper
import com.example.eziketobenna.bakingapp.recipe.presentation.data.DummyViewState
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.FakeRecipeRepository
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.FakeRecipeRepository.Companion.ERROR_MSG
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.ResponseType
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewState
import com.example.eziketobenna.bakingapp.recipe.presentation.processor.RecipeActionProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.processor.RecipeViewIntentProcessor
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Rule
import org.junit.Test

class RecipeViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val stateRecorder: FlowRecorder<RecipeViewState> = FlowRecorder(TestCoroutineScope())

    private val recipeModelMapper = RecipeModelMapper(StepModelMapper(), IngredientModelMapper())
    private val recipeViewStateReducer = RecipeViewStateReducer(recipeModelMapper)
    private val fakeRecipeRepository = FakeRecipeRepository()
    private val fetchRecipes = FetchRecipes(fakeRecipeRepository, TestPostExecutionThread())
    private val recipeActionProcessor = RecipeActionProcessor(fetchRecipes)

    private val dummyViewState = DummyViewState(recipeModelMapper)

    private val recipeViewModel: RecipeViewModel by lazy {
        RecipeViewModel(
            recipeActionProcessor,
            recipeViewStateReducer,
            RecipeViewIntentProcessor()
        )
    }

    @Test
    fun `loadInitialViewState is emitted when loadInitialAction is processed and returns data`() {
        /**
         * Pause the dispatcher so that coroutines don't run yet.
         * This allows us capture the initial viewState emitted from [RecipeViewModel.viewState].
         * That emission usually gets lost before we subscribe to the stream.
         */
        mainCoroutineRule.pauseDispatcher()
        recipeViewModel.viewState.recordWith(stateRecorder)
        // Resume the dispatcher and then run the coroutines
        // This will then emit loading and loaded state
        mainCoroutineRule.resumeDispatcher()
        assertThat(stateRecorder.takeAll())
            .containsExactlyElementsIn(dummyViewState.loadInitialViewState).inOrder()
    }

    @Test
    fun `emptyViewState is emitted when loadInitialAction is processed and no data returned`() {
        fakeRecipeRepository.responseType = ResponseType.EMPTY
        mainCoroutineRule.pauseDispatcher()
        recipeViewModel.viewState.recordWith(stateRecorder)
        mainCoroutineRule.resumeDispatcher()
        assertThat(stateRecorder.takeAll())
            .containsExactlyElementsIn(dummyViewState.emptyViewState).inOrder()
    }

    @Test
    fun `errorViewState is emitted when loadInitialAction is processed and returns error`() {
        fakeRecipeRepository.responseType = ResponseType.ERROR
        mainCoroutineRule.pauseDispatcher()
        recipeViewModel.viewState.recordWith(stateRecorder)
        mainCoroutineRule.resumeDispatcher()
        assertThat(stateRecorder.takeAll())
            .containsExactlyElementsIn(dummyViewState.noDataErrorViewState).inOrder()
    }

    @Test
    fun `refreshRecipesViewState is emitted when RefreshRecipesAction is processed and returns data`() {
        // Pause and resume dispatcher to get the initial States from LoadInitialAction
        mainCoroutineRule.pauseDispatcher()
        recipeViewModel.viewState.recordWith(stateRecorder)
        mainCoroutineRule.resumeDispatcher()
        recipeViewModel.processIntent(flowOf(RecipeViewIntent.RecipeRefreshViewIntent))
        // Assert the entire stream starting from LoadInitialAction
        assertThat(stateRecorder.takeAll()).containsExactlyElementsIn(
            dummyViewState.loadInitialViewState +
                dummyViewState.refreshRecipesViewState
        ).inOrder()
    }

    @Test
    fun `emptyViewState is emitted when RefreshRecipesAction is processed and no data returned`() {
        mainCoroutineRule.pauseDispatcher()
        fakeRecipeRepository.responseType = ResponseType.EMPTY
        recipeViewModel.viewState.recordWith(stateRecorder)
        mainCoroutineRule.resumeDispatcher()
        recipeViewModel.processIntent(flowOf(RecipeViewIntent.RecipeRefreshViewIntent))
        assertThat(stateRecorder.takeAll()).containsExactlyElementsIn(
            dummyViewState.emptyViewState + arrayOf(
                dummyViewState.emptyViewState.last().refreshingState,
                dummyViewState.emptyViewState.last().refreshingState.emptyState
            )
        ).inOrder()
    }

    @Test
    fun `noDataErrorViewState is emitted when RefreshRecipesAction is processed and no data returned`() {
        mainCoroutineRule.pauseDispatcher()
        fakeRecipeRepository.responseType = ResponseType.ERROR
        recipeViewModel.viewState.recordWith(stateRecorder)
        mainCoroutineRule.resumeDispatcher()
        recipeViewModel.processIntent(flowOf(RecipeViewIntent.RecipeRefreshViewIntent))
        assertThat(stateRecorder.takeAll()).containsExactlyElementsIn(
            dummyViewState.noDataErrorViewState + arrayOf(
                dummyViewState.noDataErrorViewState.last().refreshingState,
                dummyViewState.noDataErrorViewState.last().refreshingState
                    .noDataErrorState(ERROR_MSG)
            )
        ).inOrder()
    }

    @Test
    fun `dataAvailableViewState is emitted when RefreshRecipesAction is processed but data is already loaded`() {
        // First set the fetchRecipes response to data
        mainCoroutineRule.pauseDispatcher()
        fakeRecipeRepository.responseType = ResponseType.DATA
        recipeViewModel.viewState.recordWith(stateRecorder)
        mainCoroutineRule.resumeDispatcher()
        // Reset fetchRecipes response to error before sending new intent,
        // so as to simulate a `data available error state` [Transient error]
        fakeRecipeRepository.responseType = ResponseType.ERROR
        recipeViewModel.processIntent(flowOf(RecipeViewIntent.RecipeRefreshViewIntent))
        assertThat(stateRecorder.takeAll()).containsExactlyElementsIn(
            dummyViewState.loadInitialViewState + arrayOf(
                dummyViewState.loadInitialViewState.last().refreshingState,
                dummyViewState.loadInitialViewState.last().refreshingState
                    .dataAvailableErrorState(ERROR_MSG)
            )
        ).inOrder()
    }

    @Test
    fun `retryFetchViewState is emitted when RetryFetchAction is processed and data returned`() {
        mainCoroutineRule.pauseDispatcher()
        fakeRecipeRepository.responseType = ResponseType.ERROR
        recipeViewModel.viewState.recordWith(stateRecorder)
        mainCoroutineRule.resumeDispatcher()
        fakeRecipeRepository.responseType = ResponseType.DATA
        recipeViewModel.processIntent(flowOf(RecipeViewIntent.RetryFetchViewIntent))
        assertThat(stateRecorder.takeAll())
            .containsExactlyElementsIn(
                dummyViewState.noDataErrorViewState +
                    dummyViewState.retryFetchViewState
            ).inOrder()
    }

    @Test
    fun `emptyViewState is emitted when RetryFetchAction is processed and no data returned`() {
        mainCoroutineRule.pauseDispatcher()
        fakeRecipeRepository.responseType = ResponseType.EMPTY
        recipeViewModel.viewState.recordWith(stateRecorder)
        mainCoroutineRule.resumeDispatcher()
        recipeViewModel.processIntent(flowOf(RecipeViewIntent.RetryFetchViewIntent))
        assertThat(stateRecorder.takeAll())
            .containsExactlyElementsIn(
                dummyViewState.emptyViewState + arrayOf(
                    dummyViewState.emptyViewState.last().loadingState,
                    dummyViewState.emptyViewState.last().loadingState.emptyState
                )
            ).inOrder()
    }

    @Test
    fun `noDataErrorViewState is emitted when RetryFetchAction is processed and no data returned`() {
        mainCoroutineRule.pauseDispatcher()
        fakeRecipeRepository.responseType = ResponseType.ERROR
        recipeViewModel.viewState.recordWith(stateRecorder)
        mainCoroutineRule.resumeDispatcher()
        recipeViewModel.processIntent(flowOf(RecipeViewIntent.RetryFetchViewIntent))
        assertThat(stateRecorder.takeAll())
            .containsExactlyElementsIn(
                dummyViewState.noDataErrorViewState + arrayOf(
                    dummyViewState.emptyViewState.last().loadingState,
                    dummyViewState.emptyViewState.last().loadingState.noDataErrorState(ERROR_MSG)
                )
            ).inOrder()
    }

    @Test
    fun `dataAvailableErrorViewState is emitted when recipes are loaded but RetryFetchAction errors`() {
        mainCoroutineRule.pauseDispatcher()
        fakeRecipeRepository.responseType = ResponseType.DATA
        recipeViewModel.viewState.recordWith(stateRecorder)
        mainCoroutineRule.resumeDispatcher()
        fakeRecipeRepository.responseType = ResponseType.ERROR
        recipeViewModel.processIntent(flowOf(RecipeViewIntent.RetryFetchViewIntent))
        assertThat(stateRecorder.takeAll())
            .containsExactlyElementsIn(
                dummyViewState.loadInitialViewState +
                    dummyViewState.dataErrorViewState
            ).inOrder()
    }
}
