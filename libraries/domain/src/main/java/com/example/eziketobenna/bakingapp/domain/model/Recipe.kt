package com.example.eziketobenna.bakingapp.domain.model

data class Recipe(
    val id: Int,
    val name: String,
    val image: String,
    val servings: Int,
    val ingredients: List<Ingredient>,
    val steps: List<Step>
)
