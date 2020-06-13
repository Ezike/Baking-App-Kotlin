package com.example.eziketobenna.bakingapp.core.di.module

import com.example.eziketobenna.bakingapp.data.repository.RecipeRepositoryImpl
import com.example.eziketobenna.bakingapp.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface DataModule {

    @get:[Binds Singleton]
    val RecipeRepositoryImpl.recipeRepository: RecipeRepository
}
