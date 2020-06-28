package com.example.eziketobenna.bakingapp.data.fake

import com.example.eziketobenna.bakingapp.data.contract.RecipeRemote
import com.example.eziketobenna.bakingapp.data.model.RecipeEntity

internal class FakeRecipeRemoteImpl : RecipeRemote {

    override suspend fun fetchRecipes(): List<RecipeEntity> {
        return listOf(DummyData.recipeEntity)
    }
}
