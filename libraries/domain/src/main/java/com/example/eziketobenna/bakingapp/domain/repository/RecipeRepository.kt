package com.example.eziketobenna.bakingapp.domain.repository

import com.example.eziketobenna.bakingapp.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun fetchRecipe(): Flow<List<Recipe>>
}
