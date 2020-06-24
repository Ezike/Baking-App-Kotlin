package com.example.eziketobenna.bakingapp.stepdetail.model

data class StepUiModel(
    val stepDescription: String,
    val videoUrl: String,
    val totalSteps: Int,
    val stepIndex: Int,
    val showPrev: Boolean,
    val showNext: Boolean,
    val showVideo: Boolean
) {
    val progressText: String
        get() = "$stepIndex/$totalSteps"
}
