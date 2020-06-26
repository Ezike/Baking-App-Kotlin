package com.example.eziketobenna.bakingapp.recipedetail.di.module

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailActionProcessor
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailIntentProcessor
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewAction
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewIntent
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewResult
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewState
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewStateReducer
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
interface PresentationModule {

    @get:[Binds FeatureScope]
    val RecipeDetailActionProcessor.actionProcessor: ActionProcessor<RecipeDetailViewAction, RecipeDetailViewResult>

    @get:[Binds FeatureScope]
    val RecipeDetailIntentProcessor.intentProcessor: IntentProcessor<RecipeDetailViewIntent, RecipeDetailViewAction>

    @get:[Binds FeatureScope]
    val RecipeDetailViewStateReducer.reducer: ViewStateReducer<RecipeDetailViewState, RecipeDetailViewResult>
}
