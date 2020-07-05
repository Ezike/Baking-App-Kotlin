package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.domain.usecase.FetchRecipes
import com.example.eziketobenna.bakingapp.model.mapper.IngredientModelMapper
import com.example.eziketobenna.bakingapp.model.mapper.RecipeModelMapper
import com.example.eziketobenna.bakingapp.model.mapper.StepModelMapper
import com.example.eziketobenna.bakingapp.recipe.presentation.executor.TestPostExecutionThread
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.FakeRecipeRepository
import com.example.eziketobenna.bakingapp.recipe.presentation.fake.navigationDispatcher
import com.example.eziketobenna.bakingapp.recipe.presentation.processor.RecipeActionProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.processor.RecipeViewIntentProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.rule.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RecipeViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val recipeModelMapper = RecipeModelMapper(StepModelMapper(), IngredientModelMapper())
    private val recipeViewStateReducer = RecipeViewStateReducer(recipeModelMapper)
    private val fetchRecipes = FetchRecipes(FakeRecipeRepository(), TestPostExecutionThread())
    private val recipeActionProcessor = RecipeActionProcessor(fetchRecipes)
    private lateinit var recipeViewModel: RecipeViewModel

    @Before
    fun setup() {
        recipeViewModel = RecipeViewModel(
            recipeActionProcessor,
            recipeViewStateReducer,
            RecipeViewIntentProcessor(),
            navigationDispatcher
        )
    }

    @Test
    fun `check stuff happens`() = runBlockingTest {
        recipeViewModel.viewState.collect {
            assertThat(recipeViewModel.en).isEqualTo(it)
        }
    }
}
