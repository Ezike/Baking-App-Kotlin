package com.example.eziketobenna.bakingapp.recipe.di.component

import com.example.eziketobenna.bakingapp.core.di.component.CoreComponent
import com.example.eziketobenna.bakingapp.core.di.module.FactoriesModule
import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.di.AppComponent
import com.example.eziketobenna.bakingapp.recipe.di.module.PresentationModule
import com.example.eziketobenna.bakingapp.recipe.di.module.ViewModelModule
import com.example.eziketobenna.bakingapp.recipe.ui.RecipeFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class, AppComponent::class],
    modules = [FactoriesModule::class, ViewModelModule::class, PresentationModule::class]
)
interface RecipeComponent {

    fun inject(recipeFragment: RecipeFragment)

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent,
            appComponent: AppComponent
        ): RecipeComponent
    }
}
