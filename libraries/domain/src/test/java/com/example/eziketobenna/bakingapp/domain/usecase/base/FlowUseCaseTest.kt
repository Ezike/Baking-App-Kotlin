package com.example.eziketobenna.bakingapp.domain.usecase.base

import com.example.eziketobenna.bakingapp.domain.exception.NoParamsException
import com.example.eziketobenna.bakingapp.domain.executor.TestPostExecutionThread
import com.example.eziketobenna.bakingapp.domain.fake.ExceptionUseCase
import com.example.eziketobenna.bakingapp.domain.fake.ParamUseCase
import com.google.common.truth.Truth.assertThat
import java.net.SocketTimeoutException
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class FlowUseCaseTest {

    @Test(expected = SocketTimeoutException::class)
    fun `check that ExceptionUseCase throws exception`() = runBlockingTest {
        ExceptionUseCase(TestPostExecutionThread())()
    }

    @Test(expected = NoParamsException::class)
    fun `check that calling ParamUseCase without params throws exception`() = runBlockingTest {
        ParamUseCase(TestPostExecutionThread())()
    }

    @Test
    fun `check that ParamsUseCase returns correct data`() = runBlockingTest {
        val param = "Correct data"
        val useCase = ParamUseCase(TestPostExecutionThread())
        val result: String = useCase(param).first()
        assertThat(result).isEqualTo(param)
    }
}
