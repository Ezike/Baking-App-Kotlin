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

    @Test
    fun `check that peekContent returns the initial value`() {
        val content = 2
        val event = object : SingleEvent<Int>(content) {}
        assertThat(content).isEqualTo(event.peekContent)
    }

    @Test
    fun `check that reset allows event to be consumed again`() {
        var int = 1
        val event = object : SingleEvent<Int>(10) {}
        event.consume { value ->
            int += value
        }
        event.reset()
        event.consume { value ->
            int -= value
        }
        assertThat(int).isNotEqualTo(11)
        assertThat(int).isEqualTo(1)
    }
}
