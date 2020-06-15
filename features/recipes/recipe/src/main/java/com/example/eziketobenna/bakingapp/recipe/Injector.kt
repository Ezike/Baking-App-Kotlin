package com.example.eziketobenna.bakingapp.recipe

import com.example.eziketobenna.bakingapp.core.di.component.CoreComponent
import com.example.eziketobenna.bakingapp.recipe.di.component.DaggerRecipeComponent
import com.example.eziketobenna.bakingapp.recipe.ui.RecipeFragment
import dagger.hilt.android.EntryPointAccessors

fun injector(fragment: RecipeFragment) {
    DaggerRecipeComponent
        .factory()
        .create(
            EntryPointAccessors.fromApplication(
                fragment.requireContext().applicationContext,
                CoreComponent::class.java
            )
        ).inject(fragment)
}
