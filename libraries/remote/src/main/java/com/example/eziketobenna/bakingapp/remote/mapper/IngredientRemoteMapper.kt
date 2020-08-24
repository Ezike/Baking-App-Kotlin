package com.example.eziketobenna.bakingapp.remote.mapper

import com.example.eziketobenna.bakingapp.data.model.IngredientEntity
import com.example.eziketobenna.bakingapp.remote.mapper.base.RemoteModelMapper
import com.example.eziketobenna.bakingapp.remote.model.IngredientRemoteModel
import javax.inject.Inject

class IngredientRemoteMapper @Inject constructor() :
    RemoteModelMapper<IngredientRemoteModel, IngredientEntity> {

    override fun mapFromModel(model: IngredientRemoteModel): IngredientEntity {
        return IngredientEntity(
            model.quantity,
            model.measure,
            model.ingredient
        )
    }
}
