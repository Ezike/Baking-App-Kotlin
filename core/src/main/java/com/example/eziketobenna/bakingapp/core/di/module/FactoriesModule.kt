package com.example.eziketobenna.bakingapp.core.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.eziketobenna.bakingapp.core.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
interface FactoriesModule {

    @get:Binds
    val ViewModelFactory.viewModelFactory: ViewModelProvider.Factory
}
