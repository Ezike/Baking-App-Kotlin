package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.common_test.FlowRecorder
import com.example.eziketobenna.bakingapp.common_test.MainCoroutineRule
import com.example.eziketobenna.bakingapp.common_test.TestPostExecutionThread
import com.example.eziketobenna.bakingapp.common_test.navigationDispatcher
import com.example.eziketobenna.bakingapp.common_test.recordWith
import com.example.eziketobenna.bakingapp.domain.usecase.FetchRecipes
import com.example.eziketobenna.bakingapp.model.mapper.IngredientModelMapper
import com.example.eziketobenna.bakingapp.model.mapper.RecipeModelMapper
import com.example.eziketobenna.bakingapp.model.mapper.StepModelMapper
import com.example.eziketobenna.bakingapp.recipe.presentation.data.DummyViewState
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.FakeRecipeRepository
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewState
import com.example.eziketobenna.bakingapp.recipe.presentation.processor.RecipeActionProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.processor.RecipeViewIntentProcessor
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RecipeViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val stateRecorder: FlowRecorder<RecipeViewState> = FlowRecorder(TestCoroutineScope())

    private val recipeModelMapper = RecipeModelMapper(StepModelMapper(), IngredientModelMapper())
    private val recipeViewStateReducer = RecipeViewStateReducer(recipeModelMapper)
    private val fetchRecipes = FetchRecipes(FakeRecipeRepository(), TestPostExecutionThread())
    private val recipeActionProcessor = RecipeActionProcessor(fetchRecipes)

    private val dummyViewState = DummyViewState(recipeModelMapper)

    private lateinit var recipeViewModel: RecipeViewModel

    @Before
    fun setup() {
        mainCoroutineRule.pauseDispatcher()
        recipeViewModel = RecipeViewModel(
            recipeActionProcessor,
            recipeViewStateReducer,
            RecipeViewIntentProcessor(),
            navigationDispatcher
        )
    }

    @Test
    fun `loadInitialViewState is emitted when loadInitialAction is processed`() {
        recipeViewModel.viewState.recordWith(stateRecorder)
        mainCoroutineRule.resumeDispatcher()
        assertThat(stateRecorder.takeAll())
            .containsExactly(*dummyViewState.loadInitialViewState)
    }

    @Test
    fun `refreshRecipesViewState is emitted when RefreshRecipesAction is processed`() {
        mainCoroutineRule.resumeDispatcher()
        recipeViewModel.viewState.recordWith(stateRecorder)
        recipeViewModel.processIntent(flowOf(RecipeViewIntent.RecipeRefreshViewIntent))
        assertThat(stateRecorder.takeAll())
            .containsExactly(*dummyViewState.refreshRecipesViewState)
    }
}
