package com.example.eziketobenna.bakkingapp.model.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeModel(
    val id: Int,
    val name: String,
    val image: String,
    val servings: Int,
    val ingredients: List<IngredientModel>,
    val steps: List<StepModel>
) : Parcelable
