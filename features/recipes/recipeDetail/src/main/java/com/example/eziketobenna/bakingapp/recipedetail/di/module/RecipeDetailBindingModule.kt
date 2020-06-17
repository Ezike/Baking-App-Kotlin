package com.example.eziketobenna.bakingapp.recipedetail.di.module

import androidx.lifecycle.ViewModel
import com.example.eziketobenna.bakingapp.core.di.mapkeys.ViewModelKey
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@DisableInstallInCheck
@Module
interface RecipeDetailBindingModule {

    @get:[Binds IntoMap ViewModelKey(RecipeDetailViewModel::class)]
    val RecipeDetailViewModel.recipeDetailViewModel: ViewModel
}
