package com.example.eziketobenna.bakingapp.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.eziketobenna.bakingapp.AppExecutors;
import com.example.eziketobenna.bakingapp.data.database.RecipeDao;
import com.example.eziketobenna.bakingapp.data.model.Recipe;
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
    private final RecipeDao mRecipeDao;
    private final NetworkDataSource mNetworkDataSource;
    private AppExecutors mExecutors;
    private boolean mInitialized = false;

    private RecipeRepository(RecipeDao recipeDao, NetworkDataSource networkDataSource, AppExecutors executors) {
        mRecipeDao = recipeDao;
        mNetworkDataSource = networkDataSource;
        mExecutors = executors;

        // As long as the repository exists, observe the network LiveData.
        // If that LiveData changes, update the database.
        LiveData<List<Recipe>> networkData = mNetworkDataSource.getRecipes();
        networkData.observeForever(newRecipes -> mExecutors.diskIO().execute(() -> {
            // delete old data
            deleteOldData();
            Log.d(LOG_TAG, "Old weather deleted");
            // Insert new data
            mRecipeDao.bulkInsert(newRecipes);
            Log.d(LOG_TAG, "New values inserted");
        }));
    }

    public synchronized static RecipeRepository getInstance(RecipeDao recipeDao,
                                                            NetworkDataSource networkDataSource, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new RecipeRepository(recipeDao, networkDataSource, executors);
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

    public LiveData<List<Recipe>> getAllRecipes() {
        initializeData();
        return mRecipeDao.getAllRecipes();
    }

    /**
     * Deletes old data
     */
    private void deleteOldData() {
        mRecipeDao.deleteAllRecipes();
    }


    public Recipe getRecipe(int id) {
        return mRecipeDao.getSelectedRecipe(id);
    }

    /**
     * Fetch recipes in the background
     */
    private void startFetchRecipeService() {
        mExecutors.diskIO().execute(mNetworkDataSource::startFetchRecipeService);
    }
}
