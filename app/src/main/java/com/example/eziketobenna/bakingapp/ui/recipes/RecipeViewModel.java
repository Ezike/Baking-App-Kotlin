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

    private final LiveData<List<Recipe>> mAllRecipes;

    RecipeViewModel(RecipeRepository mRepository) {
        mAllRecipes = mRepository.getAllRecipes();
    }

    // get all Recipes
    LiveData<List<Recipe>> getAllRecipes() {
        return mAllRecipes;
    }

}
