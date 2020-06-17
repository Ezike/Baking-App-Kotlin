package com.example.eziketobenna.bakingapp.recipedetail.di.component

import androidx.fragment.app.Fragment
import com.example.eziketobenna.bakingapp.core.di.component.CoreComponent
import com.example.eziketobenna.bakingapp.core.di.module.FactoriesModule
import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.recipedetail.di.module.RecipeDetailBindingModule
import com.example.eziketobenna.bakingapp.recipedetail.di.module.RecipeDetailFragmentModule
import com.example.eziketobenna.bakingapp.recipedetail.ui.RecipeDetailFragment
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [FactoriesModule::class,
        RecipeDetailBindingModule::class,
        RecipeDetailFragmentModule::class]
)
interface RecipeDetailComponent {

    fun inject(fragment: RecipeDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent,
            @BindsInstance fragment: Fragment
        ): RecipeDetailComponent
    }
}
