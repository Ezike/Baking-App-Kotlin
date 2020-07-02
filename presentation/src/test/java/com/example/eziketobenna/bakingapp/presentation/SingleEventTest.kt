package com.example.eziketobenna.bakingapp.presentation

import com.example.eziketobenna.bakingapp.presentation.event.SingleEvent
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SingleEventTest {

    @Test
    fun `check that event is consumed`() {
        var int = 1
        val event = object : SingleEvent<Int>(10) {}
        event.consume { value ->
            int += value
        }
        assertThat(int).isEqualTo(11)
    }

    @Test
    fun `check that event is not consumed twice`() {
        var int = 1
        val event = object : SingleEvent<Int>(10) {}
        event.consume { value ->
            int += value
        }
        event.consume { value ->
            int -= value
        }
        assertThat(int).isNotEqualTo(-9)
        assertThat(int).isEqualTo(11)
    }
}
