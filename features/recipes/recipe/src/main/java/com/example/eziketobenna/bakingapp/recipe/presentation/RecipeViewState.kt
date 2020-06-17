package com.example.eziketobenna.bakingapp.recipe.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ViewState
import com.example.eziketobenna.bakkingapp.model.model.RecipeModel

sealed class RecipeViewState : ViewState {
    object Initial : RecipeViewState()
    object Loading : RecipeViewState()
    object Refreshing : RecipeViewState()
    data class Success(val recipes: List<RecipeModel>) : RecipeViewState()
    data class Error(val message: String) : RecipeViewState()
}
