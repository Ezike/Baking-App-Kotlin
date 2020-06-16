package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import javax.inject.Inject

@FeatureScope
class RecipeViewIntentProcessor @Inject constructor() :
    IntentProcessor<RecipeViewIntent, RecipeViewAction> {

    override fun intentToAction(intent: RecipeViewIntent): RecipeViewAction {
        return when (intent) {
            RecipeViewIntent.LoadInitial -> RecipeViewAction.LoadInitialAction
            RecipeViewIntent.Retry -> RecipeViewAction.RetryFetchAction
            RecipeViewIntent.Refresh -> RecipeViewAction.RefreshRecipesAction
        }
    }
}
