package com.example.eziketobenna.bakingapp.model.mapper

import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.model.IngredientModel
import com.example.eziketobenna.bakingapp.presentation.mapper.ModelMapper
import javax.inject.Inject

class IngredientModelMapper @Inject constructor() : ModelMapper<IngredientModel, Ingredient> {

    override fun mapToModel(domain: Ingredient): IngredientModel {
        return IngredientModel(
            domain.quantity,
            domain.measure,
            domain.ingredient
        )
    }

    override fun mapToDomain(model: IngredientModel): Ingredient {
        return Ingredient(
            model.quantity,
            model.measure,
            model.ingredient
        )
    }
}
