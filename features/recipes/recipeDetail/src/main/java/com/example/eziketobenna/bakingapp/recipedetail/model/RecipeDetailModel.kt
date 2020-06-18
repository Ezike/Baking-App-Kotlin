package com.example.eziketobenna.bakingapp.recipedetail.model

import androidx.annotation.StringRes

sealed class RecipeDetailModel

data class IngredientDetailItem(
    val quantity: Double,
    val measure: String,
    val ingredient: String
) : RecipeDetailModel()

data class StepDetailItem(
    val id: Int,
    val videoURL: String,
    val description: String,
    val shortDescription: String,
    val thumbnailURL: String
) : RecipeDetailModel()

data class HeaderItem(@StringRes val header: Int) : RecipeDetailModel()
