package com.example.eziketobenna.bakingapp.data.network;

import com.example.eziketobenna.bakingapp.data.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("baking.json")
    Call<List<Recipe>> getRecipe();
}
