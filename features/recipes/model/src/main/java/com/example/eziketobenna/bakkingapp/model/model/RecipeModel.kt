package com.example.eziketobenna.bakkingapp.model.model

data class RecipeModel(
    val id: Int,
    val name: String,
    val image: String,
    val servings: Int,
    val ingredients: List<IngredientModel>,
    val steps: List<StepModel>
)
