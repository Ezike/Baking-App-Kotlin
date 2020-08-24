package com.example.eziketobenna.bakingapp.model.mapper

import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.model.StepModel
import com.example.eziketobenna.bakingapp.presentation.mapper.ModelMapper
import javax.inject.Inject

class StepModelMapper @Inject constructor() : ModelMapper<StepModel, Step> {

    override fun mapToModel(domain: Step): StepModel {
        return StepModel(
            domain.id,
            domain.videoURL,
            domain.description,
            domain.shortDescription,
            domain.thumbnailURL
        )
    }

    override fun mapToDomain(model: StepModel): Step {
        return Step(
            model.id,
            model.videoURL,
            model.description,
            model.shortDescription,
            model.thumbnailURL
        )
    }
}
