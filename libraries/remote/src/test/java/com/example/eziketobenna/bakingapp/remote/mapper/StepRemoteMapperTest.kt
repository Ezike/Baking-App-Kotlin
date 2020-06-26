package com.example.eziketobenna.bakingapp.remote.mapper

import com.example.eziketobenna.bakingapp.data.model.StepEntity
import com.example.eziketobenna.bakingapp.remote.model.StepRemoteModel
import com.example.eziketobenna.bakingapp.remote.utils.stepRemoteModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StepRemoteMapperTest {

    private val stepRemoteMapper = StepRemoteMapper()

    @Test
    fun `check that id is mapped correctly`() = testData { entity, model ->
        assertThat(entity.id).isEqualTo(model.id)
    }

    @Test
    fun `check that videoUrl is mapped correctly`() = testData { entity, model ->
        assertThat(entity.videoURL).isEqualTo(model.videoURL)
    }

    @Test
    fun `check that description is mapped correctly`() = testData { entity, model ->
        assertThat(entity.description).isEqualTo(model.description)
    }

    @Test
    fun `check that shortDescription is mapped correctly`() = testData { entity, model ->
        assertThat(entity.shortDescription).isEqualTo(model.shortDescription)
    }

    @Test
    fun `check that thumbNailUrl is mapped correctly`() {
        testData { entity, model ->
            assertThat(entity.thumbnailURL).isEqualTo(model.thumbnailURL)
        }
    }

    private fun testData(action: (StepEntity, StepRemoteModel) -> Unit) {
        val model: StepRemoteModel = stepRemoteModel
        val entity: StepEntity = stepRemoteMapper.mapFromModel(model)
        action(entity, model)
    }
}
