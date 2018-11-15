package com.example.eziketobenna.bakingapp.ui.recipes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.eziketobenna.bakingapp.data.RecipeRepository;
import com.example.eziketobenna.bakingapp.data.model.Recipe;

import java.util.List;
/**
 * {@link ViewModel} for {@link RecipeFragment}
 */
class RecipeViewModel extends ViewModel {
    private final RecipeRepository mRepository;
    private final LiveData<List<Recipe>> mAllRecipes;

    RecipeViewModel(RecipeRepository mRepository) {
        this.mRepository = mRepository;
        mAllRecipes = mRepository.getAllRecipes();
    }

    LiveData<List<Recipe>> getAllRecipes() {
        return mAllRecipes;
    }

}
