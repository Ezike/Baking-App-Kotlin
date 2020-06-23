package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewResult

sealed class RecipeDetailResult : ViewResult {
    object IdleResult : RecipeDetailResult()
    data class LoadedData(val ingredients: List<Ingredient>, val steps: List<Step>) :
        RecipeDetailResult()

    data class OpenStepInfo(val step: Step, val steps: List<Step>) :
        RecipeDetailResult()
}
