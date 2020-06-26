package com.example.eziketobenna.bakingapp.remote.mapper

import com.example.eziketobenna.bakingapp.data.model.RecipeEntity
import com.example.eziketobenna.bakingapp.remote.model.IngredientRemoteModel
import com.example.eziketobenna.bakingapp.remote.model.RecipeRemoteModel
import com.example.eziketobenna.bakingapp.remote.model.StepRemoteModel
import com.example.eziketobenna.bakingapp.remote.utils.recipeRemoteModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecipeRemoteMapperTest {

    private val recipeMapper = RecipeRemoteMapper(IngredientRemoteMapper(), StepRemoteMapper())

    @Test
    fun `check stepEntity list is equal to StepRemoteModel list in size`() =
        testData { recipeEntity, model ->
            assertThat(recipeEntity.steps.size).isEqualTo(model.steps.size)
        }

    @Test
    fun `check stepEntity list has same content as StepRemoteModel list`() {
        testData { recipeEntity, model ->
            recipeEntity.steps.forEachIndexed { index, stepEntity ->
                val stepRemoteModel: StepRemoteModel = model.steps[index]
                assertThat(stepEntity.id).isEqualTo(stepRemoteModel.id)
                assertThat(stepEntity.description).isEqualTo(stepRemoteModel.description)
                assertThat(stepEntity.shortDescription).isEqualTo(stepRemoteModel.shortDescription)
                assertThat(stepEntity.videoURL).isEqualTo(stepRemoteModel.videoURL)
                assertThat(stepEntity.thumbnailURL).isEqualTo(stepRemoteModel.thumbnailURL)
            }
        }
    }

    @Test
    fun `check ingredientEntity list is equal to IngredientRemoteModel in size`() =
        testData { recipeEntity, model ->
            assertThat(recipeEntity.ingredients.size).isEqualTo(model.ingredients.size)
        }

    @Test
    fun `check ingredientEntity list has same content as IngredientRemoteModel list`() {
        testData { recipeEntity, model ->
            recipeEntity.ingredients.forEachIndexed { index, ingredientEntity ->
                val ingredientRemoteModel: IngredientRemoteModel = model.ingredients[index]
                assertThat(ingredientEntity.ingredient).isEqualTo(ingredientRemoteModel.ingredient)
                assertThat(ingredientEntity.measure).isEqualTo(ingredientRemoteModel.measure)
                assertThat(ingredientEntity.quantity).isEqualTo(ingredientRemoteModel.quantity)
            }
        }
    }

    @Test
    fun `check servings is mapped correctly`() = testData { recipeEntity, model ->
        assertThat(recipeEntity.servings).isEqualTo(model.servings)
    }

    @Test
    fun `check name is mapped correctly`() = testData { recipeEntity, model ->
        assertThat(recipeEntity.name).isEqualTo(model.name)
    }

    @Test
    fun `check image is mapped correctly`() = testData { recipeEntity, model ->
        assertThat(recipeEntity.image).isEqualTo(model.image)
    }

    @Test
    fun `check id is mapped correctly`() = testData { recipeEntity, model ->
        assertThat(recipeEntity.id).isEqualTo(model.id)
    }

    private fun testData(action: (RecipeEntity, RecipeRemoteModel) -> Unit) {
        val model: RecipeRemoteModel = recipeRemoteModel
        val recipeEntity: RecipeEntity = recipeMapper.mapFromModel(model)
        action(recipeEntity, model)
    }
}
