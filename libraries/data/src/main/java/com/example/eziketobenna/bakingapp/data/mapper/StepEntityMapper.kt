package com.example.eziketobenna.bakingapp.data.mapper

import com.example.eziketobenna.bakingapp.data.mapper.base.EntityMapper
import com.example.eziketobenna.bakingapp.data.model.StepEntity
import com.example.eziketobenna.bakingapp.domain.model.Step
import javax.inject.Inject

class StepEntityMapper @Inject constructor() : EntityMapper<StepEntity, Step> {

    override fun mapFromEntity(entity: StepEntity): Step {
        return Step(
                entity.id,
                entity.videoURL,
                entity.description,
                entity.shortDescription,
                entity.thumbnailURL
        )
    }

    override fun mapToEntity(domain: Step): StepEntity {
        return StepEntity(
                domain.id,
                domain.videoURL,
                domain.description,
                domain.shortDescription,
                domain.thumbnailURL
        )
    }
}
