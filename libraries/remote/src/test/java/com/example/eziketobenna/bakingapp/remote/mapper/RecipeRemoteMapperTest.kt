package com.example.eziketobenna.bakingapp.remote.mapper

import com.example.eziketobenna.bakingapp.data.model.IngredientEntity
import com.example.eziketobenna.bakingapp.data.model.RecipeEntity
import com.example.eziketobenna.bakingapp.data.model.StepEntity
import com.example.eziketobenna.bakingapp.remote.model.IngredientRemoteModel
import com.example.eziketobenna.bakingapp.remote.model.RecipeRemoteModel
import com.example.eziketobenna.bakingapp.remote.model.StepRemoteModel
import com.example.eziketobenna.bakingapp.remote.utils.DummyData
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecipeRemoteMapperTest {

    private val ingredientRemoteMapper = IngredientRemoteMapper()

    private val stepRemoteMapper = StepRemoteMapper()

    private val recipeMapper = RecipeRemoteMapper(ingredientRemoteMapper, stepRemoteMapper)

    @Test
    fun `check stepEntity list is equal to StepRemoteModel list in size`() {
        val stepRemoteModel: List<StepRemoteModel> = DummyData.recipeRemoteModel.steps
        val stepsEntity: List<StepEntity> = stepRemoteMapper.mapModelList(stepRemoteModel)
        assertThat(stepRemoteModel.size).isEqualTo(stepsEntity.size)
    }

    @Test
    fun `check stepEntity list has same content as StepRemoteModel list`() {
        val stepRemoteModel: List<StepRemoteModel> = DummyData.recipeRemoteModel.steps
        val stepsEntity: List<StepEntity> = stepRemoteMapper.mapModelList(stepRemoteModel)
        assertThat(stepRemoteModel.first().id).isEqualTo(stepsEntity.first().id)
        assertThat(stepRemoteModel.first().description).isEqualTo(stepsEntity.first().description)
        assertThat(stepRemoteModel.first().shortDescription).isEqualTo(stepsEntity.first().shortDescription)
        assertThat(stepRemoteModel.first().videoURL).isEqualTo(stepsEntity.first().videoURL)
        assertThat(stepRemoteModel.first().thumbnailURL).isEqualTo(stepsEntity.first().thumbnailURL)
    }

    @Test
    fun `check ingredientEntity list is equal to IngredientRemoteModel in size`() {
        val ingredientRemoteModel: List<IngredientRemoteModel> =
            DummyData.recipeRemoteModel.ingredients
        val ingredientsEntity: List<IngredientEntity> =
            ingredientRemoteMapper.mapModelList(ingredientRemoteModel)
        assertThat(ingredientRemoteModel.size).isEqualTo(ingredientsEntity.size)
    }

    @Test
    fun `check ingredientEntity list has same content as IngredientRemoteModel list`() {
        val ingredientRemoteModel: List<IngredientRemoteModel> =
            DummyData.recipeRemoteModel.ingredients
        val ingredientsEntity: List<IngredientEntity> =
            ingredientRemoteMapper.mapModelList(ingredientRemoteModel)
        assertThat(ingredientRemoteModel.first().ingredient).isEqualTo(ingredientsEntity.first().ingredient)
        assertThat(ingredientRemoteModel.first().measure).isEqualTo(ingredientsEntity.first().measure)
        assertThat(ingredientRemoteModel.first().quantity).isEqualTo(ingredientsEntity.first().quantity)
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
        val model: RecipeRemoteModel = DummyData.recipeRemoteModel
        val recipeEntity: RecipeEntity = recipeMapper.mapFromModel(model)
        action(recipeEntity, model)
    }
}
