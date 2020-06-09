package com.example.eziketobenna.bakingapp.data.contract

import com.example.eziketobenna.bakingapp.data.model.RecipeEntity

interface RecipeRemote {
    suspend fun fetchRecipes(): List<RecipeEntity>
}
