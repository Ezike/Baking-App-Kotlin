package com.example.eziketobenna.bakingapp.recipedetail.model

import android.os.Parcelable
import com.example.eziketobenna.bakkingapp.model.model.StepModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StepsInfoModel(
    val index: Int,
    val steps: List<StepModel>,
    val step: StepModel
) : Parcelable
