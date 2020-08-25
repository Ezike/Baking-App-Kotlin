package com.example.eziketobenna.bakingapp.data.repository

import com.example.eziketobenna.bakingapp.data.contract.RecipeRemote
import com.example.eziketobenna.bakingapp.data.mapper.RecipeEntityMapper
import com.example.eziketobenna.bakingapp.data.model.RecipeEntity
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeRemote: RecipeRemote,
    private val recipeMapper: RecipeEntityMapper
) : RecipeRepository {

    override fun fetchRecipes(): Flow<List<Recipe>> {
        return flow {
            val recipes: List<RecipeEntity> = recipeRemote.fetchRecipes()
            emit(recipeMapper.mapFromEntityList(recipes))
        }
    }
}
