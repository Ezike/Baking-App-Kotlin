package com.example.eziketobenna.bakingapp.recipe.presentation.processor

import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.LoadInitialAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.RefreshRecipesAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewAction.RetryFetchAction
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent.LoadInitialViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent.RecipeRefreshViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent.RetryFetchViewIntent
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecipeViewIntentProcessorTest {

    private val recipeViewIntentProcessor = RecipeViewIntentProcessor()

    @Test
    fun `check that LoadInitialViewIntent is mapped to LoadInitialAction`() {
        val action: RecipeViewAction =
            recipeViewIntentProcessor.intentToAction(LoadInitialViewIntent)
        assertThat(action).isInstanceOf(LoadInitialAction::class.java)
    }

    @Test
    fun `check that RecipeRetryViewIntent is mapped to RetryFetchAction`() {
        val action: RecipeViewAction =
            recipeViewIntentProcessor.intentToAction(RetryFetchViewIntent)
        assertThat(action).isInstanceOf(RetryFetchAction::class.java)
    }

    @Test
    fun `check that RecipeRefreshViewIntent is mapped to RefreshRecipesAction`() {
        val action: RecipeViewAction =
            recipeViewIntentProcessor.intentToAction(RecipeRefreshViewIntent)
        assertThat(action).isInstanceOf(RefreshRecipesAction::class.java)
    }
}
