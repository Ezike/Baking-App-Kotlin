package com.example.eziketobenna.bakingapp.recipedetail.di

import com.example.eziketobenna.bakingapp.recipedetail.di.component.DaggerRecipeDetailComponent
import com.example.eziketobenna.bakingapp.recipedetail.ui.RecipeDetailFragment

internal fun inject(fragment: RecipeDetailFragment) {
    DaggerRecipeDetailComponent
        .factory()
        .create()
        .inject(fragment)
}
