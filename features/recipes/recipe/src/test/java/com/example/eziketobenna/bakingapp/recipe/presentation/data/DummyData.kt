package com.example.eziketobenna.bakingapp.recipe.presentation.data

import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.model.mapper.RecipeModelMapper

internal object DummyData {

    val recipe = Recipe(
        id = 3,
        name = "Burritos",
        image = "imgurl.com",
        servings = 1,
        ingredients = listOf(ingredient),
        steps = listOf(step)
    )

    private val ingredient: Ingredient
        get() = Ingredient(
            quantity = 1.4,
            measure = "3",
            ingredient = "salt"
        )

    private val step: Step
        get() = Step(
            id = 1,
            description = "pour stuff",
            shortDescription = "pour",
            videoURL = "url.com",
            thumbnailURL = "thumb.com"
        )

    fun recipeModelList(recipeModelMapper: RecipeModelMapper): List<RecipeModel> =
        recipeModelMapper.mapToModelList(listOf(recipe))
}
