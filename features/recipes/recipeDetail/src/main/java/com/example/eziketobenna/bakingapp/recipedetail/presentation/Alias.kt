package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer

typealias DetailActionProcessor =
    @JvmSuppressWildcards ActionProcessor<RecipeDetailViewAction, RecipeDetailViewResult>

typealias DetailIntentProcessor =
    @JvmSuppressWildcards IntentProcessor<RecipeDetailViewIntent, RecipeDetailViewAction>

typealias DetailViewStateReducer =
    @JvmSuppressWildcards ViewStateReducer<RecipeDetailViewState, RecipeDetailViewResult>
