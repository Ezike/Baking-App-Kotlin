package com.example.eziketobenna.bakingapp.recipe.presentation.processor

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.LoadInitialAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.RefreshRecipesAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.RetryFetchAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent.LoadInitialViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent.RecipeRefreshViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent.RecipeRetryViewIntent
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
