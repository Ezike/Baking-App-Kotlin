package com.example.eziketobenna.bakingapp.model.mapper

import com.example.eziketobenna.bakingapp.domain.model.Recipe
import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.presentation.mapper.ModelMapper
import javax.inject.Inject

class RecipeModelMapper @Inject constructor(
    private val stepModelMapper: StepModelMapper,
    private val ingredientModelMapper: IngredientModelMapper
) : ModelMapper<RecipeModel, Recipe> {

    override fun mapToModel(domain: Recipe): RecipeModel {
        return RecipeModel(
            domain.id,
            domain.name,
            domain.image,
            domain.servings,
            ingredientModelMapper.mapToModelList(domain.ingredients),
            stepModelMapper.mapToModelList(domain.steps)
        )
    }

    override fun mapToDomain(model: RecipeModel): Recipe {
        return Recipe(
                model.id,
                model.name,
                model.image,
                model.servings,
                ingredientModelMapper.mapToDomainList(model.ingredients),
                stepModelMapper.mapToDomainList(model.steps)
        )
    }
}
