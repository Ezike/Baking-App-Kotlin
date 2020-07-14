package com.example.eziketobenna.bakingapp.core.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.eziketobenna.bakingapp.core.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@[Module DisableInstallInCheck]
interface FactoryModule {

    @get:Binds
    val ViewModelFactory.viewModelFactory: ViewModelProvider.Factory
}
