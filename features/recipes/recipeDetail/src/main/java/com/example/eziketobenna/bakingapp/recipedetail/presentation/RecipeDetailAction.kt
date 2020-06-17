package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewAction

sealed class RecipeDetailAction : ViewAction {
    object Idle : RecipeDetailAction()
    data class LoadRecipeDetailAction(val ingredients: List<Ingredient>, val steps: List<Step>) :
        RecipeDetailAction()
}
