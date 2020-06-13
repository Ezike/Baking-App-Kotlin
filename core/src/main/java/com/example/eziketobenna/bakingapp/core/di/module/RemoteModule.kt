package com.example.eziketobenna.bakingapp.core.di.module

import com.example.eziketobenna.bakingapp.data.contract.RecipeRemote
import com.example.eziketobenna.bakingapp.remote.ApiService
import com.example.eziketobenna.bakingapp.remote.ApiServiceFactory
import com.example.eziketobenna.bakingapp.remote.impl.RecipeRemoteImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface RemoteModule {

    @get:[Binds Singleton]
    val RecipeRemoteImpl.bindRemote: RecipeRemote

    @get:[Provides Singleton]
    val provideMoshi: Moshi
        get() = Moshi.Builder()
                .add(KotlinJsonAdapterFactory()).build()

    @[Provides Singleton]
    fun provideApiService(moshi: Moshi): ApiService =
            ApiServiceFactory.makeAPiService(BuildConfig.DEBUG, moshi)
}
