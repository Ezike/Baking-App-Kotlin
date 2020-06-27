package com.example.eziketobenna.bakingapp.recipe.di

import com.example.eziketobenna.bakingapp.core.di.component.CoreComponent
import com.example.eziketobenna.bakingapp.di.AppComponent
import com.example.eziketobenna.bakingapp.recipe.di.component.DaggerRecipeComponent
import com.example.eziketobenna.bakingapp.recipe.ui.RecipeFragment
import dagger.hilt.android.EntryPointAccessors

internal fun inject(fragment: RecipeFragment) {
    DaggerRecipeComponent
        .factory()
        .create(coreComponent(fragment), appComponent(fragment))
        .inject(fragment)
}

private fun coreComponent(fragment: RecipeFragment): CoreComponent =
    EntryPointAccessors.fromApplication(
        fragment.requireContext().applicationContext,
        CoreComponent::class.java
    )

private fun appComponent(fragment: RecipeFragment): AppComponent =
    EntryPointAccessors.fromActivity(
        fragment.requireActivity(),
        AppComponent::class.java
    )
