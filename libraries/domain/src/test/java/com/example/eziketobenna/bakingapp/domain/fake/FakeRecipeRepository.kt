package com.example.eziketobenna.bakingapp.domain.fake

import com.example.eziketobenna.bakingapp.domain.data.DummyData
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class FakeRecipeRepository : RecipeRepository {

    override fun fetchRecipes(): Flow<List<Recipe>> {
        return flowOf(listOf(DummyData.recipe))
    }
}
