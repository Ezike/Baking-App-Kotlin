package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewAction
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewIntent
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewResult
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewState

typealias DetailActionProcessor =
    @JvmSuppressWildcards ActionProcessor<RecipeDetailViewAction, RecipeDetailViewResult>

typealias DetailIntentProcessor =
    @JvmSuppressWildcards IntentProcessor<RecipeDetailViewIntent, RecipeDetailViewAction>

typealias DetailViewStateReducer =
    @JvmSuppressWildcards ViewStateReducer<RecipeDetailViewState, RecipeDetailViewResult>
