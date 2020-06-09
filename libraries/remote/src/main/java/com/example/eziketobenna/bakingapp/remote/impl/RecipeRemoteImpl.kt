package com.example.eziketobenna.bakingapp.remote.impl

import com.example.eziketobenna.bakingapp.data.contract.RecipeRemote
import com.example.eziketobenna.bakingapp.data.model.RecipeEntity
import com.example.eziketobenna.bakingapp.remote.ApiService
import com.example.eziketobenna.bakingapp.remote.mapper.RecipeRemoteMapper
import com.example.eziketobenna.bakingapp.remote.model.RecipeRemoteModel
import javax.inject.Inject

class RecipeRemoteImpl @Inject constructor(
    private val apiService: ApiService,
    private val recipeRemoteMapper: RecipeRemoteMapper
) : RecipeRemote {

    override suspend fun fetchRecipes(): List<RecipeEntity> {
        val recipes: List<RecipeRemoteModel> = apiService.fetchRecipes()
        return recipeRemoteMapper.mapModelList(recipes)
    }
}
