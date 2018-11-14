package com.example.eziketobenna.bakingapp.ui;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.eziketobenna.bakingapp.data.RecipeRepository;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link RecipeRepository}
 */
public class RecipeViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final RecipeRepository mRepository;

    public RecipeViewModelFactory(RecipeRepository mRepository) {
        this.mRepository = mRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RecipeViewModel(mRepository);
    }
}
