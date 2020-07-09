package com.example.eziketobenna.bakingapp.domain.usecase

import com.example.eziketobenna.bakingapp.domain.data.DummyData
import com.example.eziketobenna.bakingapp.domain.executor.TestPostExecutionThread
import com.example.eziketobenna.bakingapp.domain.fake.FakeRecipeRepository
import com.example.eziketobenna.bakingapp.domain.fake.FakeRecipeRepository.Companion.ERROR_MSG
import com.example.eziketobenna.bakingapp.domain.fake.ResponseType
import com.example.eziketobenna.bakingapp.domain.fake.assertThrows
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.google.common.truth.Truth.assertThat
import java.net.SocketTimeoutException
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertThrows
import org.junit.Test

class FetchRecipesTest {

    private val fakeRecipeRepository = FakeRecipeRepository()

    private val fetchRecipesUseCase =
        FetchRecipes(fakeRecipeRepository, TestPostExecutionThread())

    @Test
    fun `check that calling fetchRecipes returns recipe list`() = runBlockingTest {
        fakeRecipeRepository.responseType = ResponseType.DATA
        val recipes: List<Recipe> = fetchRecipesUseCase().first()
        assertThat(recipes.size).isAtLeast(1)
    }

    @Test
    fun `check that calling fetchRecipes returns correct data`() = runBlockingTest {
        fakeRecipeRepository.responseType = ResponseType.DATA
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
    fun `check that calling fetchRecipes returns empty list if response is empty`() = runBlockingTest {
        fakeRecipeRepository.responseType = ResponseType.EMPTY
        val recipes: List<Recipe> = fetchRecipesUseCase().first()
        assertThat(recipes).isEmpty()
    }

    @Test
    fun `check that calling fetchRecipes returns error if call fails`() = runBlockingTest {
        fakeRecipeRepository.responseType = ResponseType.ERROR
        val exception: SocketTimeoutException = assertThrows {
            fetchRecipesUseCase().collect()
        }
        assertThat(exception).hasMessageThat().isEqualTo(ERROR_MSG)
    }
}
