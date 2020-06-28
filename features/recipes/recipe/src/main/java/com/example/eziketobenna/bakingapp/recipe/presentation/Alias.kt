package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer

typealias RecipeViewActionProcessor =
    @JvmSuppressWildcards ActionProcessor<RecipeViewAction, RecipeViewResult>

typealias RecipeStateReducer =
    @JvmSuppressWildcards ViewStateReducer<RecipeViewState, RecipeViewResult>

typealias RecipeIntentProcessor =
    @JvmSuppressWildcards IntentProcessor<RecipeViewIntent, RecipeViewAction>
