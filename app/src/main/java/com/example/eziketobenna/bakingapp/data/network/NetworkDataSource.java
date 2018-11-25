package com.example.eziketobenna.bakingapp.data.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.eziketobenna.bakingapp.AppExecutors;
import com.example.eziketobenna.bakingapp.data.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Provides an API for doing all operations with the api data
 */
public class NetworkDataSource {
    private static final String LOG_TAG = NetworkDataSource.class.getSimpleName();
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static NetworkDataSource sInstance;
    private final Context mContext;

    // LiveData storing the latest downloaded Recipe
    private final MutableLiveData<List<Recipe>> mDownloadedRecipes;
    private final AppExecutors mExecutors;

    public NetworkDataSource(Context context, AppExecutors mExecutors) {
        mContext = context.getApplicationContext();
        this.mExecutors = mExecutors;
        mDownloadedRecipes = new MutableLiveData<>();
    }

    /**
     * Get the singleton for this class
     */
    public static NetworkDataSource getInstance(Context context, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NetworkDataSource(context.getApplicationContext(), executors);
                Log.d(LOG_TAG, "Made new network data source");

            }
        }
        return sInstance;
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
    fetch recipes from network
     */
    public void fetchRecipes() {
        Log.d(LOG_TAG, "Fetch recipe started");
        mExecutors.networkIO().execute(() -> {
            ApiInterface apiService = ApiClient.getClient();
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
