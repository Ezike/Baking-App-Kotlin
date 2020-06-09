package com.example.eziketobenna.bakingapp.remote.mapper

import com.example.eziketobenna.bakingapp.data.model.StepEntity
import com.example.eziketobenna.bakingapp.remote.mapper.base.RemoteModelMapper
import com.example.eziketobenna.bakingapp.remote.model.StepRemoteModel
import javax.inject.Inject

class StepRemoteMapper @Inject constructor() : RemoteModelMapper<StepRemoteModel, StepEntity> {

    override fun mapFromModel(model: StepRemoteModel): StepEntity {
        return StepEntity(
                model.id,
                model.videoURL,
                model.description,
                model.shortDescription,
                model.thumbnailURL
        )
    }
}
