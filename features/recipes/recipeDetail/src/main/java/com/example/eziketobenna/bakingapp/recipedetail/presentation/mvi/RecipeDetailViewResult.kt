package com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi

import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewResult

sealed class RecipeDetailViewResult : ViewResult {
    object IdleResult : RecipeDetailViewResult()
    data class LoadedData(val ingredients: List<Ingredient>, val steps: List<Step>) :
        RecipeDetailViewResult()

    data class OpenStepInfo(val step: Step, val steps: List<Step>) :
        RecipeDetailViewResult()
}
