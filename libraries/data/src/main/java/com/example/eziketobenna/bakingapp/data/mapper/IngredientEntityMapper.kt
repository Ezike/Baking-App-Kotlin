package com.example.eziketobenna.bakingapp.data.mapper

import com.example.eziketobenna.bakingapp.data.mapper.base.EntityMapper
import com.example.eziketobenna.bakingapp.data.model.IngredientEntity
import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import javax.inject.Inject

class IngredientEntityMapper @Inject constructor() : EntityMapper<IngredientEntity, Ingredient> {

    override fun mapFromEntity(entity: IngredientEntity): Ingredient {
        return Ingredient(
            entity.quantity,
            entity.measure,
            entity.ingredient
        )
    }

    override fun mapToEntity(domain: Ingredient): IngredientEntity {
        return IngredientEntity(
            domain.quantity,
            domain.measure,
            domain.ingredient
        )
    }
}
