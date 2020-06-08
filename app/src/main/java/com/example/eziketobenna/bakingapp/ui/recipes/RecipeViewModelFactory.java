package com.example.eziketobenna.bakingapp.ui.recipes;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.eziketobenna.bakingapp.data.RecipeRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link RecipeRepository}
 */
@Singleton
public class RecipeViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final RecipeRepository mRepository;

    @Inject
    public RecipeViewModelFactory(RecipeRepository mRepository) {
        this.mRepository = mRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RecipeViewModel(mRepository);
    }
}
