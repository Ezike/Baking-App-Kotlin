package com.example.eziketobenna.bakingapp.domain.usecase

import com.example.eziketobenna.bakingapp.domain.data.DummyData
import com.example.eziketobenna.bakingapp.domain.executor.TestPostExecutionThread
import com.example.eziketobenna.bakingapp.domain.fake.FakeRecipeRepository
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class FetchRecipesTest {

    private val fetchRecipesUseCase =
        FetchRecipes(FakeRecipeRepository(), TestPostExecutionThread())

    @Test
    fun `check that calling fetchRecipes returns recipe list`() = runBlockingTest {
        val recipes: List<Recipe> = fetchRecipesUseCase().first()
        assertThat(recipes.size).isAtLeast(1)
    }

    @Test
    fun `check that calling fetchRecipes first item returns correct data`() = runBlockingTest {
        val recipes: List<Recipe> = fetchRecipesUseCase().first()
        val recipe: Recipe = recipes.first()
        assertThat(recipe.id).isEqualTo(DummyData.recipe.id)
        assertThat(recipe.image).isEqualTo(DummyData.recipe.image)
        assertThat(recipe.servings).isEqualTo(DummyData.recipe.servings)
        assertThat(recipe.name).isEqualTo(DummyData.recipe.name)
        assertThat(recipe.ingredients).isEqualTo(listOf(DummyData.ingredient))
        assertThat(recipe.steps).isEqualTo(listOf(DummyData.step))
    }

    @Test
    fun `check that calling fetchRecipes last item returns correct data`() = runBlockingTest {
        val recipes: List<Recipe> = fetchRecipesUseCase().first()
        val recipe: Recipe = recipes.last()
        assertThat(recipe.id).isEqualTo(2)
        assertThat(recipe.image).isEqualTo("")
        assertThat(recipe.servings).isEqualTo(DummyData.recipe.servings)
        assertThat(recipe.name).isEqualTo(DummyData.recipe.name)
        assertThat(recipe.ingredients).isEqualTo(listOf(DummyData.ingredient))
        assertThat(recipe.steps).isEqualTo(listOf<Step>())
    }
}
