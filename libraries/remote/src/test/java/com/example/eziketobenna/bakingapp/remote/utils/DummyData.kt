package com.example.eziketobenna.bakingapp.remote.utils

import com.example.eziketobenna.bakingapp.remote.model.IngredientRemoteModel
import com.example.eziketobenna.bakingapp.remote.model.RecipeRemoteModel
import com.example.eziketobenna.bakingapp.remote.model.StepRemoteModel

internal object DummyData {
    val recipeRemoteModel: RecipeRemoteModel
        get() = RecipeRemoteModel(
            id = 1,
            name = "Eba",
            image = "some url",
            servings = 3,
            ingredients = listOf(ingredientRemoteModel),
            steps = listOf(stepRemoteModel)
        )

    val ingredientRemoteModel: IngredientRemoteModel
        get() = IngredientRemoteModel(
            quantity = 3.4,
            measure = "4",
            ingredient = "Ata rodo"
        )

    val stepRemoteModel: StepRemoteModel
        get() = StepRemoteModel(
            id = 0,
            videoURL = "another url",
            description = "mehh",
            shortDescription = "me",
            thumbnailURL = "lalala"
        )
}
