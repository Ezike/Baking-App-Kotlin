package com.example.eziketobenna.bakingapp.recipe.presentation.processor

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction.LoadInitialAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction.RefreshRecipesAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewAction.RetryFetchAction
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewIntent.LoadInitialViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewIntent.RecipeRefreshViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewIntent.RecipeRetryViewIntent
import javax.inject.Inject

@FeatureScope
class RecipeViewIntentProcessor @Inject constructor() :
    IntentProcessor<RecipeViewIntent, RecipeViewAction> {

    override fun intentToAction(intent: RecipeViewIntent): RecipeViewAction {
        return when (intent) {
            LoadInitialViewIntent -> LoadInitialAction
            RecipeRetryViewIntent -> RetryFetchAction
            RecipeRefreshViewIntent -> RefreshRecipesAction
        }
    }
}
