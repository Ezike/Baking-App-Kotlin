package com.example.eziketobenna.bakingapp.data.model

data class RecipeEntity(
    val id: Int,
    val name: String,
    val image: String,
    val servings: Int,
    val ingredients: List<IngredientEntity>,
    val steps: List<StepEntity>
)
