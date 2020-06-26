package com.example.eziketobenna.bakingapp.recipe.di.module

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeActionProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewIntentProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewResult
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewState
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewStateReducer
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
interface PresentationModule {

    @get:[Binds FeatureScope]
    val RecipeActionProcessor.actionProcessor: ActionProcessor<RecipeViewAction, RecipeViewResult>

    @get:[Binds FeatureScope]
    val RecipeViewIntentProcessor.intentProcessor: IntentProcessor<RecipeViewIntent, RecipeViewAction>

    @get:[Binds FeatureScope]
    val RecipeViewStateReducer.reducer: ViewStateReducer<RecipeViewState, RecipeViewResult>
}
