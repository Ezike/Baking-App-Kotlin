package com.example.eziketobenna.bakingapp.stepdetail.di

import com.example.eziketobenna.bakingapp.core.di.module.FactoriesModule
import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.stepdetail.ui.StepDetailFragment
import dagger.Component

@FeatureScope
@Component(modules = [FactoriesModule::class, ViewModelModule::class])
interface StepDetailComponent {

    fun inject(fragment: StepDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(): StepDetailComponent
    }
}
