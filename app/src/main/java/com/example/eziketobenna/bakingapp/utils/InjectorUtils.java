package com.example.eziketobenna.bakingapp.utils;

import android.content.Context;

import com.example.eziketobenna.bakingapp.AppExecutors;
import com.example.eziketobenna.bakingapp.data.RecipeRepository;
import com.example.eziketobenna.bakingapp.data.database.AppDatabase;
import com.example.eziketobenna.bakingapp.data.network.NetworkDataSource;
import com.example.eziketobenna.bakingapp.ui.RecipeViewModelFactoy;

/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
public class InjectorUtils {
    public static RecipeRepository provideRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        NetworkDataSource networkDataSource = NetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return RecipeRepository.getInstance(appDatabase.recipeDao(), networkDataSource, executors);
    }

    public static NetworkDataSource provideNetworkDataSource(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        return NetworkDataSource.getInstance(context.getApplicationContext(), executors);

    }

    public static RecipeViewModelFactoy provideRecipeViewModelFactory(Context context) {
        RecipeRepository repository = provideRepository(context.getApplicationContext());
        return new RecipeViewModelFactoy(repository);
    }
}
