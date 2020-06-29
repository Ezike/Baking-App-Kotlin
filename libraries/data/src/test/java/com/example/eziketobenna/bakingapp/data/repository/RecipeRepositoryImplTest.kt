package com.example.eziketobenna.bakingapp.data.repository

import com.example.eziketobenna.bakingapp.data.fake.DummyData
import com.example.eziketobenna.bakingapp.data.fake.FakeRecipeRemoteImpl
import com.example.eziketobenna.bakingapp.data.mapper.IngredientEntityMapper
import com.example.eziketobenna.bakingapp.data.mapper.RecipeEntityMapper
import com.example.eziketobenna.bakingapp.data.mapper.StepEntityMapper
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class RecipeRepositoryImplTest {

    private val recipeEntityMapper =
        RecipeEntityMapper(IngredientEntityMapper(), StepEntityMapper())
    private val recipeRepository =
        RecipeRepositoryImpl(FakeRecipeRemoteImpl(), recipeEntityMapper)

    @Test
    fun `check that fetchRecipes returns data`() = runBlockingTest {
        val recipes: List<Recipe> = recipeRepository.fetchRecipes().first()
        assertThat(recipes).isNotEmpty()
    }

    @Test
    fun `check that FetchRecipes returns correct data`() = runBlockingTest {
        testData { recipe ->
            assertThat(recipe.name).isEqualTo(DummyData.recipe.name)
            assertThat(recipe.id).isEqualTo(DummyData.recipe.id)
            assertThat(recipe.image).isEqualTo(DummyData.recipe.image)
            assertThat(recipe.servings).isEqualTo(DummyData.recipe.servings)
        }
    }

    @Test
    fun `check that FetchRecipes returns correct ingredient list`() = runBlockingTest {
        testData { recipe ->
            assertThat(recipe.ingredients.size).isAtLeast(1)
        }
    }

    @Test
    fun `check that FetchRecipes returns correct steps list`() = runBlockingTest {
        testData { recipe ->
            assertThat(recipe.steps.size).isAtLeast(1)
        }
    }

    private suspend fun testData(recipe: (Recipe) -> Unit) {
        val recipes: List<Recipe> = recipeRepository.fetchRecipes().first()
        recipe(recipes.first())
    }
}
