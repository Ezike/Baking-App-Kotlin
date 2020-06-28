package com.example.eziketobenna.bakingapp.data.mapper

import com.example.eziketobenna.bakingapp.data.fake.DummyData
import com.example.eziketobenna.bakingapp.data.model.IngredientEntity
import com.example.eziketobenna.bakingapp.data.model.RecipeEntity
import com.example.eziketobenna.bakingapp.data.model.StepEntity
import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RecipeEntityMapperTest {

    private val ingredientEntityMapper = IngredientEntityMapper()

    private val stepEntityMapper = StepEntityMapper()

    private val recipeEntityMapper =
        RecipeEntityMapper(ingredientEntityMapper, stepEntityMapper)

    @Test
    fun `check that mapFromEntity maps data correctly`() {
        val recipeEntity: RecipeEntity = DummyData.recipeEntity
        val recipeDomain: Recipe = recipeEntityMapper.mapFromEntity(recipeEntity)
        assertThat(recipeEntity.name).isEqualTo(recipeDomain.name)
        assertThat(recipeEntity.id).isEqualTo(recipeDomain.id)
        assertThat(recipeEntity.servings).isEqualTo(recipeDomain.servings)
        assertThat(recipeEntity.image).isEqualTo(recipeDomain.image)
    }

    @Test
    fun `check that mapToEntity maps data correctly`() {
        val recipe: Recipe = DummyData.recipe
        val recipeEntity: RecipeEntity = recipeEntityMapper.mapToEntity(recipe)
        assertThat(recipeEntity.name).isEqualTo(recipe.name)
        assertThat(recipeEntity.id).isEqualTo(recipe.id)
        assertThat(recipeEntity.servings).isEqualTo(recipe.servings)
        assertThat(recipeEntity.image).isEqualTo(recipe.image)
    }

    @Test
    fun `check that Ingredient mapFromDomainList maps data correctly`() {
        val ingredients: List<Ingredient> = DummyData.recipe.ingredients
        val ingredientsEntity: List<IngredientEntity> =
            ingredientEntityMapper.mapFromDomainList(ingredients)
        assertThat(ingredients.first().ingredient).isEqualTo(ingredientsEntity.first().ingredient)
        assertThat(ingredients.first().quantity).isEqualTo(ingredientsEntity.first().quantity)
        assertThat(ingredients.first().measure).isEqualTo(ingredientsEntity.first().measure)
    }

    @Test
    fun `check that Ingredient mapFromDomainList returns same sized list`() {
        val ingredients: List<Ingredient> = DummyData.recipe.ingredients
        val ingredientsEntity: List<IngredientEntity> =
            ingredientEntityMapper.mapFromDomainList(ingredients)
        assertThat(ingredients.size).isEqualTo(ingredientsEntity.size)
    }

    @Test
    fun `check that Step mapFromDomainList maps data correctly`() {
        val steps: List<Step> = DummyData.recipe.steps
        val stepsEntity: List<StepEntity> =
            stepEntityMapper.mapFromDomainList(steps)
        assertThat(steps.first().id).isEqualTo(stepsEntity.first().id)
        assertThat(steps.first().description).isEqualTo(stepsEntity.first().description)
        assertThat(steps.first().shortDescription).isEqualTo(stepsEntity.first().shortDescription)
        assertThat(steps.first().videoURL).isEqualTo(stepsEntity.first().videoURL)
        assertThat(steps.first().thumbnailURL).isEqualTo(stepsEntity.first().thumbnailURL)
    }

    @Test
    fun `check that Step mapFromDomainList returns same sized list`() {
        val steps: List<Step> = DummyData.recipe.steps
        val stepsEntity: List<StepEntity> =
            stepEntityMapper.mapFromDomainList(steps)
        assertThat(steps.size).isEqualTo(stepsEntity.size)
    }

    @Test
    fun `check that Ingredient mapFromEntityList maps data correctly`() {
        val ingredientsEntity: List<IngredientEntity> = DummyData.recipeEntity.ingredients
        val ingredients: List<Ingredient> =
            ingredientEntityMapper.mapFromEntityList(ingredientsEntity)
        assertThat(ingredientsEntity.first().ingredient).isEqualTo(ingredients.first().ingredient)
        assertThat(ingredientsEntity.first().quantity).isEqualTo(ingredients.first().quantity)
        assertThat(ingredientsEntity.first().measure).isEqualTo(ingredients.first().measure)
    }

    @Test
    fun `check that Ingredient mapFromEntityList returns same sized list`() {
        val ingredientsEntity: List<IngredientEntity> = DummyData.recipeEntity.ingredients
        val ingredients: List<Ingredient> =
            ingredientEntityMapper.mapFromEntityList(ingredientsEntity)
        assertThat(ingredientsEntity.size).isEqualTo(ingredients.size)
    }

    @Test
    fun ` check that Step mapFromEntityList maps data correctly`() {
        val stepsEntity: List<StepEntity> = DummyData.recipeEntity.steps
        val steps: List<Step> =
            stepEntityMapper.mapFromEntityList(stepsEntity)
        assertThat(stepsEntity.first().id).isEqualTo(steps.first().id)
        assertThat(stepsEntity.first().description).isEqualTo(steps.first().description)
        assertThat(stepsEntity.first().shortDescription).isEqualTo(steps.first().shortDescription)
        assertThat(stepsEntity.first().videoURL).isEqualTo(steps.first().videoURL)
        assertThat(stepsEntity.first().thumbnailURL).isEqualTo(steps.first().thumbnailURL)
    }

    @Test
    fun ` check that Step mapFromEntityList returns same sized list`() {
        val stepsEntity: List<StepEntity> = DummyData.recipeEntity.steps
        val steps: List<Step> =
            stepEntityMapper.mapFromEntityList(stepsEntity)
        assertThat(stepsEntity.size).isEqualTo(steps.size)
    }
}
