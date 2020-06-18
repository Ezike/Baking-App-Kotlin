package com.example.eziketobenna.bakingapp.recipedetail.model

import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.presentation.mapper.ModelMapper
import javax.inject.Inject

class StepDetailMapper @Inject constructor() : ModelMapper<StepDetailItem, Step> {

    override fun mapToModel(domain: Step): StepDetailItem {
        return StepDetailItem(
            domain.id,
            domain.videoURL,
            domain.description,
            domain.shortDescription,
            domain.thumbnailURL
        )
    }

    override fun mapToDomain(model: StepDetailItem): Step {
        return Step(
            model.id,
            model.videoURL,
            model.description,
            model.shortDescription,
            model.thumbnailURL
        )
    }
}
