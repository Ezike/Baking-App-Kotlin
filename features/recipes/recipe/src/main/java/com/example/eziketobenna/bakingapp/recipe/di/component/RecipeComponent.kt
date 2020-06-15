package com.example.eziketobenna.bakingapp.recipe.di.component

import androidx.fragment.app.Fragment
import com.example.eziketobenna.bakingapp.core.di.component.CoreComponent
import com.example.eziketobenna.bakingapp.core.di.module.FactoriesModule
import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.recipe.di.module.BindingModule
import com.example.eziketobenna.bakingapp.recipe.di.module.RecipeFragmentModule
import com.example.eziketobenna.bakingapp.recipe.ui.RecipeFragment
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [FactoriesModule::class, BindingModule::class, RecipeFragmentModule::class]
)
interface RecipeComponent {

    fun inject(recipeFragment: RecipeFragment)

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent,
            @BindsInstance fragment: Fragment
        ): RecipeComponent
    }
}
