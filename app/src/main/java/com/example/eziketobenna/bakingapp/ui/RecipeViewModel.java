package com.example.eziketobenna.bakingapp.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.eziketobenna.bakingapp.data.RecipeRepository;
import com.example.eziketobenna.bakingapp.data.database.Recipe;

import java.util.List;


/**
 * {@link ViewModel} for {@link RecipeFragment}
 */
public class RecipeViewModel extends ViewModel {
    private final RecipeRepository mRepository;
    private final LiveData<List<Recipe>> mAllRecipes;

    public RecipeViewModel(RecipeRepository mRepository) {
        this.mRepository = mRepository;
        mAllRecipes = mRepository.getAllRecipes();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return mAllRecipes;
    }
}
