package com.example.eziketobenna.bakingapp.data.repository

import com.example.eziketobenna.bakingapp.data.contract.RecipeRemote
import com.example.eziketobenna.bakingapp.data.mapper.RecipeEntityMapper
import com.example.eziketobenna.bakingapp.data.model.RecipeEntity
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.repository.RecipeRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRepositoryImpl @Inject constructor(
    private val recipeRemote: RecipeRemote,
    private val recipeMapper: RecipeEntityMapper
) : RecipeRepository {

    override fun fetchRecipe(): Flow<List<Recipe>> {
        return flow {
            val recipes: List<RecipeEntity> = recipeRemote.fetchRecipes()
            emit(recipeMapper.mapFromEntityList(recipes))
        }
    }
}
