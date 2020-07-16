package com.example.eziketobenna.bakingapp.core.di.module

import com.example.eziketobenna.bakingapp.core.imageLoader.ImageLoader
import com.example.eziketobenna.bakingapp.core.imageLoader.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface ImageLoaderModule {

    @get:[Binds Singleton]
    val ImageLoaderImpl.imageLoader: ImageLoader
}
