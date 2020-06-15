package com.example.eziketobenna.bakingapp.core.di.component

import com.example.eziketobenna.bakingapp.core.imageLoader.ImageLoader
import com.example.eziketobenna.bakingapp.domain.executor.PostExecutionThread
import com.example.eziketobenna.bakingapp.domain.repository.RecipeRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface CoreComponent {
    val imageLoader: ImageLoader
    val recipeRepository: RecipeRepository
    val postExecutionThread: PostExecutionThread
}
