package com.example.eziketobenna.bakingapp.remote.mapper

import com.example.eziketobenna.bakingapp.data.model.IngredientEntity
import com.example.eziketobenna.bakingapp.remote.model.IngredientRemoteModel
import com.example.eziketobenna.bakingapp.remote.utils.ingredientRemoteModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class IngredientRemoteMapperTest {

    private val ingredientRemoteMapper = IngredientRemoteMapper()

    @Test
    fun `check that quantity is mapped correctly`() = testData { entity, model ->
        assertThat(entity.quantity).isEqualTo(model.quantity)
    }

    @Test
    fun `check that measure is mapped correctly`() = testData { entity, model ->
        assertThat(entity.measure).isEqualTo(model.measure)
    }

    @Test
    fun `check that ingredient is mapped correctly`() = testData { entity, model ->
        assertThat(entity.ingredient).isEqualTo(model.ingredient)
    }

    private fun testData(action: (IngredientEntity, IngredientRemoteModel) -> Unit) {
        val model: IngredientRemoteModel = ingredientRemoteModel
        val entity: IngredientEntity = ingredientRemoteMapper.mapFromModel(ingredientRemoteModel)
        action(entity, model)
    }
}
