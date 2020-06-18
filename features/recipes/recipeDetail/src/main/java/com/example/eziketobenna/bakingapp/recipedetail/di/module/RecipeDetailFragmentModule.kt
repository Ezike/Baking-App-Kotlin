package com.example.eziketobenna.bakingapp.recipedetail.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
object RecipeDetailFragmentModule {

    @Provides
    fun recipeViewModel(
        factory: ViewModelProvider.Factory,
        fragment: Fragment
    ): Lazy<RecipeDetailViewModel> =
        fragment.viewModels { factory }
}
