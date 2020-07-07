package com.example.eziketobenna.bakingapp.model.mapper

import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.model.DummyData
import com.example.eziketobenna.bakingapp.model.IngredientModel
import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.model.StepModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecipeModelMapperTest {

    private val ingredientModelMapper = IngredientModelMapper()

    private val stepModelMapper = StepModelMapper()

    private val recipeModelMapper = RecipeModelMapper(stepModelMapper, ingredientModelMapper)

    @Test
    fun `mapToModel maps data correctly`() {
        val recipe: Recipe = DummyData.recipe
        val recipeModel: RecipeModel = recipeModelMapper.mapToModel(recipe)
        assertThat(recipe.name).isEqualTo(recipeModel.name)
        assertThat(recipe.id).isEqualTo(recipeModel.id)
        assertThat(recipe.servings).isEqualTo(recipeModel.servings)
        assertThat(recipe.image).isEqualTo(recipeModel.image)
    }

    @Test
    fun `mapToDomain maps data correctly`() {
        val recipeModel: RecipeModel = DummyData.recipeModel
        val recipe: Recipe = recipeModelMapper.mapToDomain(recipeModel)
        assertThat(recipeModel.name).isEqualTo(recipe.name)
        assertThat(recipeModel.id).isEqualTo(recipe.id)
        assertThat(recipeModel.servings).isEqualTo(recipe.servings)
        assertThat(recipeModel.image).isEqualTo(recipe.image)
    }

    @Test
    fun `ingredient mapToModelList maps data correctly`() {
        val ingredients: List<Ingredient> = DummyData.recipe.ingredients
        val ingredientsModel: List<IngredientModel> =
            ingredientModelMapper.mapToModelList(ingredients)
        assertThat(ingredients.first().ingredient).isEqualTo(ingredientsModel.first().ingredient)
        assertThat(ingredients.first().quantity).isEqualTo(ingredientsModel.first().quantity)
        assertThat(ingredients.first().measure).isEqualTo(ingredientsModel.first().measure)
    }

    @Test
    fun `ingredient mapToModelList returns same sized list`() {
        val ingredients: List<Ingredient> = DummyData.recipe.ingredients
        val ingredientsModel: List<IngredientModel> =
            ingredientModelMapper.mapToModelList(ingredients)
        assertThat(ingredients.size).isEqualTo(ingredientsModel.size)
    }

    @Test
    fun `ingredient mapToDomainList maps data correctly`() {
        val ingredientsModel: List<IngredientModel> = DummyData.recipeModel.ingredients
        val ingredients: List<Ingredient> = ingredientModelMapper.mapToDomainList(ingredientsModel)
        assertThat(ingredientsModel.first().ingredient).isEqualTo(ingredients.first().ingredient)
        assertThat(ingredientsModel.first().quantity).isEqualTo(ingredients.first().quantity)
        assertThat(ingredientsModel.first().measure).isEqualTo(ingredients.first().measure)
    }

    @Test
    fun `ingredient mapToDomainList returns same sized list`() {
        val ingredientsModel: List<IngredientModel> = DummyData.recipeModel.ingredients
        val ingredients: List<Ingredient> = ingredientModelMapper.mapToDomainList(ingredientsModel)
        assertThat(ingredientsModel.size).isEqualTo(ingredients.size)
    }

    @Test
    fun `step mapToModelList maps data correctly`() {
        val steps: List<Step> = DummyData.recipe.steps
        val stepsModel: List<StepModel> = stepModelMapper.mapToModelList(steps)
        assertThat(steps.first().id).isEqualTo(stepsModel.first().id)
        assertThat(steps.first().description).isEqualTo(stepsModel.first().description)
        assertThat(steps.first().shortDescription).isEqualTo(stepsModel.first().shortDescription)
        assertThat(steps.first().videoURL).isEqualTo(stepsModel.first().videoURL)
        assertThat(steps.first().thumbnailURL).isEqualTo(stepsModel.first().thumbnailURL)
    }

    @Test
    fun `step mapToModelList returns same sized list`() {
        val steps: List<Step> = DummyData.recipe.steps
        val stepsModel: List<StepModel> = stepModelMapper.mapToModelList(steps)
        assertThat(steps.size).isEqualTo(stepsModel.size)
    }

    @Test
    fun `step mapToDomainList maps data correctly`() {
        val stepsModel: List<StepModel> = DummyData.recipeModel.steps
        val steps: List<Step> = stepModelMapper.mapToDomainList(stepsModel)
        assertThat(stepsModel.first().id).isEqualTo(steps.first().id)
        assertThat(stepsModel.first().description).isEqualTo(steps.first().description)
        assertThat(stepsModel.first().shortDescription).isEqualTo(steps.first().shortDescription)
        assertThat(stepsModel.first().videoURL).isEqualTo(steps.first().videoURL)
        assertThat(stepsModel.first().thumbnailURL).isEqualTo(steps.first().thumbnailURL)
    }

    @Test
    fun `step mapToDomainList returns same sized list`() {
        val stepsModel: List<StepModel> = DummyData.recipeModel.steps
        val steps: List<Step> = stepModelMapper.mapToDomainList(stepsModel)
        assertThat(stepsModel.size).isEqualTo(steps.size)
    }
}
