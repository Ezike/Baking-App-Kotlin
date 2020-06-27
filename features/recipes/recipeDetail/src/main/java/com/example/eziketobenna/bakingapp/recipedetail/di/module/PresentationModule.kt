package com.example.eziketobenna.bakingapp.recipedetail.di.module

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.recipedetail.presentation.DetailActionProcessor
import com.example.eziketobenna.bakingapp.recipedetail.presentation.DetailIntentProcessor
import com.example.eziketobenna.bakingapp.recipedetail.presentation.DetailViewStateReducer
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewStateReducer
import com.example.eziketobenna.bakingapp.recipedetail.presentation.processor.RecipeDetailActionProcessor
import com.example.eziketobenna.bakingapp.recipedetail.presentation.processor.RecipeDetailIntentProcessor
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
interface PresentationModule {

    @get:[Binds FeatureScope]
    val RecipeDetailActionProcessor.actionProcessor: DetailActionProcessor

    @get:[Binds FeatureScope]
    val RecipeDetailIntentProcessor.intentProcessor: DetailIntentProcessor

    @get:[Binds FeatureScope]
    val RecipeDetailViewStateReducer.reducer: DetailViewStateReducer
}
