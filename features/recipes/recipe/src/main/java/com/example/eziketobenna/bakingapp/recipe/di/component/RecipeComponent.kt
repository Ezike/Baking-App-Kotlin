package com.example.eziketobenna.bakingapp.recipe.di.component

import com.example.eziketobenna.bakingapp.core.di.component.CoreComponent
import com.example.eziketobenna.bakingapp.core.di.module.FactoriesModule
import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.recipe.di.module.RecipeBindingModule
import com.example.eziketobenna.bakingapp.recipe.ui.RecipeFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [FactoriesModule::class, RecipeBindingModule::class]
)
interface RecipeComponent {

    fun inject(recipeFragment: RecipeFragment)

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): RecipeComponent
    }
}
