package com.example.eziketobenna.bakingapp.data.mapper

import com.example.eziketobenna.bakingapp.data.mapper.base.EntityMapper
import com.example.eziketobenna.bakingapp.data.model.RecipeEntity
import com.example.eziketobenna.bakingapp.domain.model.Recipe
import javax.inject.Inject

class RecipeEntityMapper @Inject constructor(
    private val ingredientMapper: IngredientEntityMapper,
    private val stepMapper: StepEntityMapper
) : EntityMapper<RecipeEntity, Recipe> {

    override fun mapFromEntity(entity: RecipeEntity): Recipe {
        return Recipe(
            entity.id,
            entity.name,
            entity.image,
            entity.servings,
            ingredientMapper.mapFromEntityList(entity.ingredients),
            stepMapper.mapFromEntityList(entity.steps)
        )
    }

    override fun mapToEntity(domain: Recipe): RecipeEntity {
        return RecipeEntity(
            domain.id,
            domain.name,
            domain.image,
            domain.servings,
            ingredientMapper.mapFromDomainList(domain.ingredients),
            stepMapper.mapFromDomainList(domain.steps)
        )
    }
}
