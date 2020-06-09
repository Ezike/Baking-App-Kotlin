package com.example.eziketobenna.bakingapp.remote

import com.example.eziketobenna.bakingapp.remote.model.RecipeRemoteModel
import retrofit2.http.GET

interface ApiService {
    @GET("baking.json")
    suspend fun fetchRecipes(): List<RecipeRemoteModel>
}
