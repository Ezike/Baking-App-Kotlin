package com.example.eziketobenna.bakingapp.utils;

import android.content.Context;

import com.example.eziketobenna.bakingapp.AppExecutors;
import com.example.eziketobenna.bakingapp.data.RecipeRepository;
import com.example.eziketobenna.bakingapp.data.database.AppDatabase;
import com.example.eziketobenna.bakingapp.data.network.NetworkDataSource;
import com.example.eziketobenna.bakingapp.ui.recipes.RecipeViewModelFactory;

/**
 * Provides static methods to inject the various classes needed
 */
public class InjectorUtils {
    // Creates instance of {@link RecipeRepository}
    public static RecipeRepository provideRepository(Context context) {
        AppDatabase appDatabase = AppDatabase.getInstance(context.getApplicationContext());
        AppExecutors executors = AppExecutors.getInstance();
        NetworkDataSource networkDataSource = NetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return RecipeRepository.getInstance(appDatabase.recipeDao(), networkDataSource, executors);
    }

    // Creates instance of {@link NetworkDataSource}
    public static NetworkDataSource provideNetworkDataSource(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        return NetworkDataSource.getInstance(context.getApplicationContext(), executors);
    }

    // Creates instance of {@link RecipeViewModelFactory}
    public static RecipeViewModelFactory provideRecipeViewModelFactory(Context context) {
        RecipeRepository repository = provideRepository(context.getApplicationContext());
        return new RecipeViewModelFactory(repository);
    }
}
