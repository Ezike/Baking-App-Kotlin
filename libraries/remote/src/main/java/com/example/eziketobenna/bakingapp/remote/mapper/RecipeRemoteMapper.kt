package com.example.eziketobenna.bakingapp.remote.mapper

import com.example.eziketobenna.bakingapp.data.model.RecipeEntity
import com.example.eziketobenna.bakingapp.remote.mapper.base.RemoteModelMapper
import com.example.eziketobenna.bakingapp.remote.model.RecipeRemoteModel
import javax.inject.Inject

class RecipeRemoteMapper @Inject constructor(
    private val ingredientRemoteMapper: IngredientRemoteMapper,
    private val stepRemoteMapper: StepRemoteMapper
) : RemoteModelMapper<RecipeRemoteModel, RecipeEntity> {

    override fun mapFromModel(model: RecipeRemoteModel): RecipeEntity {
        return RecipeEntity(
            model.id,
            model.name,
            model.image,
            model.servings,
            ingredientRemoteMapper.mapModelList(model.ingredients),
            stepRemoteMapper.mapModelList(model.steps)
        )
    }
}
