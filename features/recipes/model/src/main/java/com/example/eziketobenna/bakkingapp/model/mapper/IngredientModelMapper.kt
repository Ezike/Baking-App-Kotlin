package com.example.eziketobenna.bakkingapp.model.mapper

import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.presentation.mapper.ModelMapper
import com.example.eziketobenna.bakkingapp.model.model.IngredientModel
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
