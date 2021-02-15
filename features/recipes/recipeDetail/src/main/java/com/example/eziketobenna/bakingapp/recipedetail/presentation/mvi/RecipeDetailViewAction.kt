package com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi

import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewAction

sealed class RecipeDetailViewAction : ViewAction {
    data class LoadRecipeDetailAction(
        val ingredients: List<Ingredient>,
        val steps: List<Step>
    ) : RecipeDetailViewAction()

    data class OpenStepInfoViewAction(
        val step: Step,
        val steps: List<Step>
    ) : RecipeDetailViewAction()
}
