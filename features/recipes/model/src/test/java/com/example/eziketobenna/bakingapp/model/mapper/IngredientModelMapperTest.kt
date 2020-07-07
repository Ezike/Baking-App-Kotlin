package com.example.eziketobenna.bakingapp.model.mapper

import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.model.DummyData
import com.example.eziketobenna.bakingapp.model.IngredientModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class IngredientModelMapperTest {

    private val ingredientModelMapper = IngredientModelMapper()

    @Test
    fun `mapToModel maps data correctly`() {
        val ingredient: Ingredient = DummyData.ingredient
        val ingredientModel: IngredientModel = ingredientModelMapper.mapToModel(ingredient)
        assertThat(ingredient.ingredient).isEqualTo(ingredientModel.ingredient)
        assertThat(ingredient.quantity).isEqualTo(ingredientModel.quantity)
        assertThat(ingredient.measure).isEqualTo(ingredientModel.measure)
    }

    @Test
    fun `mapToDomain maps data correctly`() {
        val ingredientModel: IngredientModel = DummyData.ingredientModel
        val ingredient: Ingredient = ingredientModelMapper.mapToDomain(ingredientModel)
        assertThat(ingredientModel.ingredient).isEqualTo(ingredient.ingredient)
        assertThat(ingredientModel.quantity).isEqualTo(ingredient.quantity)
        assertThat(ingredientModel.measure).isEqualTo(ingredient.measure)
    }
}
