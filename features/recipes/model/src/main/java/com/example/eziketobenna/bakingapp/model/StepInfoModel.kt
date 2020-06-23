package com.example.eziketobenna.bakingapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StepInfoModel(
    val steps: List<StepModel>,
    val step: StepModel
) : Parcelable
