package com.example.eziketobenna.bakingapp.recipe.di.component

import com.example.eziketobenna.bakingapp.core.di.component.CoreComponent
import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.recipe.di.module.BindingModule
import com.example.eziketobenna.bakingapp.recipe.ui.RecipeFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [BindingModule::class]
)
interface RecipeComponent {

    fun inject(recipeFragment: RecipeFragment)

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): RecipeComponent
    }
}
