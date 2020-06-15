package com.example.eziketobenna.bakingapp.recipe.di.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eziketobenna.bakingapp.core.di.mapkeys.ViewModelKey
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@DisableInstallInCheck
@Module
interface BindingModule {

    @get:[Binds IntoMap ViewModelKey(RecipeViewModel::class)]
    val RecipeViewModel.recipeViewModel: ViewModel
}

@DisableInstallInCheck
@Module
object RecipeFragmentModule {

    @JvmStatic
    @Provides
    fun recipeViewModel(factory: ViewModelProvider.Factory, fragment: Fragment): Lazy<RecipeViewModel> =
        fragment.viewModels { factory }
}
