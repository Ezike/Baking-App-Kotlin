package com.example.eziketobenna.bakingapp.data.mapper

import com.example.eziketobenna.bakingapp.data.fake.DummyData
import com.example.eziketobenna.bakingapp.data.model.IngredientEntity
import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class IngredientEntityMapperTest {

    private val ingredientEntityMapper = IngredientEntityMapper()

    @Test
    fun `check that mapFromEntity maps data correctly`() {
        val ingredientEntity: IngredientEntity = DummyData.ingredientEntity
        val ingredientDomain: Ingredient = ingredientEntityMapper.mapFromEntity(ingredientEntity)
        assertThat(ingredientEntity.ingredient).isEqualTo(ingredientDomain.ingredient)
        assertThat(ingredientEntity.quantity).isEqualTo(ingredientDomain.quantity)
        assertThat(ingredientEntity.measure).isEqualTo(ingredientDomain.measure)
    }

    @Test
    fun `check that mapToEntity maps data correctly`() {
        val ingredientDomain: Ingredient = DummyData.ingredient
        val ingredientEntity: IngredientEntity = ingredientEntityMapper.mapToEntity(ingredientDomain)
        assertThat(ingredientDomain.ingredient).isEqualTo(ingredientEntity.ingredient)
        assertThat(ingredientDomain.quantity).isEqualTo(ingredientEntity.quantity)
        assertThat(ingredientDomain.measure).isEqualTo(ingredientEntity.measure)
    }
}
