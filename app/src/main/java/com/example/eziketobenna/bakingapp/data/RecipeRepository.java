package com.example.eziketobenna.bakingapp.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.eziketobenna.bakingapp.AppExecutors;
import com.example.eziketobenna.bakingapp.data.database.Recipe;
import com.example.eziketobenna.bakingapp.data.network.NetworkDataSource;

import java.util.List;

/**
 * Acts as a mediator between {@link NetworkDataSource}
 * and {@link com.example.eziketobenna.bakingapp.data.database.RecipeDao} to provide data to other modules
 */
public class RecipeRepository {
    private static final String LOG_TAG = RecipeRepository.class.getSimpleName();
    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static RecipeRepository sInstance;
    private final NetworkDataSource mNetworkDataSource;
    private AppExecutors mExecutors;
    private boolean mInitialized = false;

    private RecipeRepository(NetworkDataSource networkDataSource, AppExecutors executors) {
        mNetworkDataSource = networkDataSource;
        mExecutors = executors;

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        LiveData<List<Recipe>> networkData = mNetworkDataSource.getRecipes();
        networkData.observeForever(recipes -> mExecutors.diskIO().execute(() -> {

        }));
    }

    public synchronized static RecipeRepository getInstance(
            NetworkDataSource networkDataSource, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RecipeRepository(networkDataSource, executors);
                Log.d(LOG_TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    private synchronized void initializeData() {
        // Only perform initialization once per app lifetime. If initialization has already been
        // performed, we have nothing to do in this method.
        if (mInitialized) return;
        mInitialized = true;
        startFetchRecipeService();
    }

    /**
     * Fetch recipes in the background
     */
    private void startFetchRecipeService() {
        mExecutors.diskIO().execute(mNetworkDataSource::startFetchRecipeService);
    }
}
