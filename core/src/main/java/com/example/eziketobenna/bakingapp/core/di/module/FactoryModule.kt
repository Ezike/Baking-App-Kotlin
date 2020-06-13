package com.example.eziketobenna.bakingapp.core.di.module

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.example.eziketobenna.bakingapp.core.factory.DynamicFragmentFactory
import com.example.eziketobenna.bakingapp.core.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface FactoryModule {

    @get:[Singleton Binds]
    val ViewModelFactory.viewModelFactory: ViewModelProvider.Factory

    @get:[Singleton Binds]
    val DynamicFragmentFactory.fragmentFactory: FragmentFactory
}
