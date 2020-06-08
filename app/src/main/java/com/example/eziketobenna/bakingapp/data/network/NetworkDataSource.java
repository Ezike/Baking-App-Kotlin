package com.example.eziketobenna.bakingapp.data.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.eziketobenna.bakingapp.AppExecutors;
import com.example.eziketobenna.bakingapp.data.model.Recipe;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Provides an API for doing all operations on network data
 */
@Singleton
public class NetworkDataSource {
    private static final String LOG_TAG = NetworkDataSource.class.getSimpleName();
    private final Context mContext;

    @Inject
    ApiInterface apiService;

    // LiveData storing the latest downloaded Recipe
    private final MutableLiveData<List<Recipe>> mDownloadedRecipes;
    private final AppExecutors mExecutors;

    @Inject
    public NetworkDataSource(Context context, AppExecutors mExecutors) {
        mContext = context.getApplicationContext();
        this.mExecutors = mExecutors;
        mDownloadedRecipes = new MutableLiveData<>();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mDownloadedRecipes;
    }

    /**
     * Starts an intent service to fetch the recipe.
     */
    public void startFetchRecipeService() {
        Intent intentToFetch = new Intent(mContext, RecipeIntentService.class);
        mContext.startService(intentToFetch);
        Log.d(LOG_TAG, "Service created");
    }

    /*
    fetch recipes from api using retrofit
     */
    public void fetchRecipes() {
        mExecutors.networkIO().execute(() -> {
            Call<List<Recipe>> call = apiService.getRecipe();
            call.enqueue(new Callback<List<Recipe>>() {
                @Override
                public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                    int statusCode = response.code();
                    Log.d(LOG_TAG, "onResponse: " + statusCode);
                    List<Recipe> recipes = response.body();
                    if (recipes != null) {
                        Log.d(LOG_TAG, "Received " + recipes.size() + " recipes");
                    }
                    mDownloadedRecipes.postValue(recipes);
                }
                @Override
                public void onFailure(Call<List<Recipe>> call, Throwable t) {
                    t.printStackTrace();
                    Log.d(LOG_TAG, "onFailure: " + t.getMessage());
                }
            });

        });
    }
}
