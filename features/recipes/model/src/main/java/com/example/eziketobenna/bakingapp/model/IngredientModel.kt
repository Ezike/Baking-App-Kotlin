package com.example.eziketobenna.bakingapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class IngredientModel(
    val quantity: Double,
    val measure: String,
    val ingredient: String
) : Parcelable
