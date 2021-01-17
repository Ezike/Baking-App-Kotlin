package com.example.eziketobenna.bakingapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeModel(
    val id: Int,
    val name: String,
    val image: String,
    val servings: Int,
    val ingredients: List<IngredientModel>,
    val steps: List<StepModel>
) : Parcelable
