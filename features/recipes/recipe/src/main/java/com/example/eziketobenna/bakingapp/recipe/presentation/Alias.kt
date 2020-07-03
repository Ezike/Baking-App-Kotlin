package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewResult
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewState

typealias RecipeViewActionProcessor =
    @JvmSuppressWildcards ActionProcessor<RecipeViewAction, RecipeViewResult>

typealias RecipeStateReducer =
    @JvmSuppressWildcards ViewStateReducer<RecipeViewState, RecipeViewResult>

typealias RecipeIntentProcessor =
    @JvmSuppressWildcards IntentProcessor<RecipeViewIntent, RecipeViewAction>
