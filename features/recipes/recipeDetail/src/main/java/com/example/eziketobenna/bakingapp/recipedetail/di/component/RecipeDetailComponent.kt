package com.example.eziketobenna.bakingapp.recipedetail.di.component

import com.example.eziketobenna.bakingapp.core.di.module.FactoriesModule
import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.recipedetail.di.module.RecipeDetailBindingModule
import com.example.eziketobenna.bakingapp.recipedetail.ui.RecipeDetailFragment
import dagger.Component

@FeatureScope
@Component(modules = [FactoriesModule::class, RecipeDetailBindingModule::class])
interface RecipeDetailComponent {

    fun inject(fragment: RecipeDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(): RecipeDetailComponent
    }
}
