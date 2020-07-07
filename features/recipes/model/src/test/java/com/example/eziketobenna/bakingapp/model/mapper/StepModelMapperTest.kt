package com.example.eziketobenna.bakingapp.model.mapper

import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.model.DummyData
import com.example.eziketobenna.bakingapp.model.StepModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StepModelMapperTest {

    private val stepModelMapper = StepModelMapper()

    @Test
    fun `mapToModel maps data correctly`() {
        val step: Step = DummyData.step
        val stepModel: StepModel = stepModelMapper.mapToModel(step)
        assertThat(step.id).isEqualTo(stepModel.id)
        assertThat(step.shortDescription).isEqualTo(stepModel.shortDescription)
        assertThat(step.description).isEqualTo(stepModel.description)
        assertThat(step.videoURL).isEqualTo(stepModel.videoURL)
        assertThat(step.thumbnailURL).isEqualTo(stepModel.thumbnailURL)
    }

    @Test
    fun `mapToDomain maps data correctly`() {
        val stepModel: StepModel = DummyData.stepModel
        val step: Step = stepModelMapper.mapToDomain(stepModel)
        assertThat(stepModel.id).isEqualTo(stepModel.id)
        assertThat(stepModel.shortDescription).isEqualTo(step.shortDescription)
        assertThat(stepModel.description).isEqualTo(step.description)
        assertThat(stepModel.videoURL).isEqualTo(step.videoURL)
        assertThat(stepModel.thumbnailURL).isEqualTo(step.thumbnailURL)
    }
}
