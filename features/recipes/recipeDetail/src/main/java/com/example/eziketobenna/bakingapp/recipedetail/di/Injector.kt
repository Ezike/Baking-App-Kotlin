package com.example.eziketobenna.bakingapp.recipedetail.di

import com.example.eziketobenna.bakingapp.core.di.component.CoreComponent
import com.example.eziketobenna.bakingapp.recipedetail.di.component.DaggerRecipeDetailComponent
import com.example.eziketobenna.bakingapp.recipedetail.ui.RecipeDetailFragment
import dagger.hilt.android.EntryPointAccessors

internal fun inject(fragment: RecipeDetailFragment) {
    DaggerRecipeDetailComponent
        .factory()
        .create(
            EntryPointAccessors.fromApplication(
                fragment.requireContext().applicationContext,
                CoreComponent::class.java
            ), fragment
        )
        .inject(fragment)
}
