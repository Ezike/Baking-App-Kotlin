package com.example.eziketobenna.bakingapp.data.fake

import com.example.eziketobenna.bakingapp.data.model.IngredientEntity
import com.example.eziketobenna.bakingapp.data.model.RecipeEntity
import com.example.eziketobenna.bakingapp.data.model.StepEntity
import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.model.Step

internal object DummyData {
    val recipeEntity = RecipeEntity(
        id = 1,
        name = "Eba",
        image = "imgurl.com",
        servings = 3,
        ingredients = listOf(ingredientEntity),
        steps = listOf(stepEntity)
    )

    val ingredientEntity: IngredientEntity
        get() = IngredientEntity(
            quantity = 4.4,
            measure = "2",
            ingredient = "pepper"
        )
    val stepEntity: StepEntity
        get() = StepEntity(
            id = 1,
            description = "pour stuff",
            shortDescription = "pour",
            videoURL = "url.com",
            thumbnailURL = "thumb.com"
        )

    val recipe = Recipe(
        id = 1,
        name = "Eba",
        image = "imgurl.com",
        servings = 3,
        ingredients = listOf(ingredient),
        steps = listOf(step)
    )

    val ingredient: Ingredient
        get() = Ingredient(
            quantity = 4.4,
            measure = "2",
            ingredient = "pepper"
        )
    val step: Step
        get() = Step(
            id = 1,
            description = "pour stuff",
            shortDescription = "pour",
            videoURL = "url.com",
            thumbnailURL = "thumb.com"
        )
}
