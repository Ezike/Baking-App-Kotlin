package com.example.eziketobenna.bakingapp.recipedetail.model

import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.presentation.mapper.ModelMapper
import javax.inject.Inject

class IngredientDetailMapper @Inject constructor() :
    ModelMapper<IngredientDetailItem, Ingredient> {

    override fun mapToModel(domain: Ingredient): IngredientDetailItem {
        return IngredientDetailItem(
            domain.quantity,
            domain.measure,
            domain.ingredient
        )
    }

    override fun mapToDomain(model: IngredientDetailItem): Ingredient {
        return Ingredient(
            model.quantity,
            model.measure,
            model.ingredient
        )
    }
}
