package com.example.eziketobenna.bakingapp.recipe.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
object RecipeFragmentModule {

    @Provides
    fun recipeViewModel(
        factory: ViewModelProvider.Factory,
        fragment: Fragment
    ): Lazy<RecipeViewModel> =
        fragment.viewModels { factory }
}
