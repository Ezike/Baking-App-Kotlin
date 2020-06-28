package com.example.eziketobenna.bakingapp.data.mapper

import com.example.eziketobenna.bakingapp.data.fake.DummyData
import com.example.eziketobenna.bakingapp.data.model.StepEntity
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StepEntityMapperTest {

    private val stepEntityMapper = StepEntityMapper()

    @Test
    fun `check that mapFromEntity maps data correctly`() {
        val stepEntity: StepEntity = DummyData.stepEntity
        val stepDomain: Step = stepEntityMapper.mapFromEntity(stepEntity)
        assertThat(stepEntity.id).isEqualTo(stepDomain.id)
        assertThat(stepEntity.shortDescription).isEqualTo(stepDomain.shortDescription)
        assertThat(stepEntity.description).isEqualTo(stepDomain.description)
        assertThat(stepEntity.videoURL).isEqualTo(stepDomain.videoURL)
        assertThat(stepEntity.thumbnailURL).isEqualTo(stepDomain.thumbnailURL)
    }

    @Test
    fun `check that mapToEntity maps data correctly`() {
        val stepDomain: Step = DummyData.step
        val stepEntity: StepEntity = stepEntityMapper.mapToEntity(stepDomain)
        assertThat(stepDomain.id).isEqualTo(stepEntity.id)
        assertThat(stepDomain.shortDescription).isEqualTo(stepEntity.shortDescription)
        assertThat(stepDomain.description).isEqualTo(stepEntity.description)
        assertThat(stepDomain.videoURL).isEqualTo(stepEntity.videoURL)
        assertThat(stepDomain.thumbnailURL).isEqualTo(stepEntity.thumbnailURL)
    }
}
