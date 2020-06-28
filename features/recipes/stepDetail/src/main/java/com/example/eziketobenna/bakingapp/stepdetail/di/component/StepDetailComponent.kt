package com.example.eziketobenna.bakingapp.stepdetail.di.component

import com.example.eziketobenna.bakingapp.core.di.module.FactoriesModule
import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.di.AppComponent
import com.example.eziketobenna.bakingapp.stepdetail.di.module.PresentationModule
import com.example.eziketobenna.bakingapp.stepdetail.di.module.ViewModelModule
import com.example.eziketobenna.bakingapp.stepdetail.ui.StepDetailFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [AppComponent::class],
    modules = [FactoriesModule::class, ViewModelModule::class, PresentationModule::class]
)
interface StepDetailComponent {

    fun inject(fragment: StepDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): StepDetailComponent
    }
}
