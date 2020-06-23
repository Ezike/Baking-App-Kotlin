package com.example.eziketobenna.bakingapp.core.di.module

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.core.factory.DynamicFragmentFactory
import com.example.eziketobenna.bakingapp.core.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
interface FactoriesModule {

    @get:[FeatureScope Binds]
    val ViewModelFactory.viewModelFactory: ViewModelProvider.Factory

    @get:[FeatureScope Binds]
    val DynamicFragmentFactory.fragmentFactory: FragmentFactory
}
