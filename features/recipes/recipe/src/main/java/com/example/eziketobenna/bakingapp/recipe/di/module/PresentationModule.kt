package com.example.eziketobenna.bakingapp.recipe.di.module

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeIntentProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeStateReducer
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewActionProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewStateReducer
import com.example.eziketobenna.bakingapp.recipe.presentation.processor.RecipeActionProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.processor.RecipeViewIntentProcessor
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
interface PresentationModule {

    @get:[Binds FeatureScope]
    val RecipeActionProcessor.actionProcessor: RecipeViewActionProcessor

    @get:[Binds FeatureScope]
    val RecipeViewIntentProcessor.intentProcessor: RecipeIntentProcessor

    @get:[Binds FeatureScope]
    val RecipeViewStateReducer.reducer: RecipeStateReducer
}
