package com.example.eziketobenna.bakingapp.domain.fake

import com.example.eziketobenna.bakingapp.domain.exception.requireParams
import com.example.eziketobenna.bakingapp.domain.executor.PostExecutionThread
import com.example.eziketobenna.bakingapp.domain.fake.FakeRecipeRepository.Companion.ERROR_MSG
import com.example.eziketobenna.bakingapp.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert
import org.junit.function.ThrowingRunnable
import java.net.SocketTimeoutException

class ExceptionUseCase(postExecutionThread: PostExecutionThread) :
    FlowUseCase<Unit, Unit>(postExecutionThread) {

    override fun execute(params: Unit?): Flow<Unit> {
        return flow {
            throw SocketTimeoutException(ERROR_MSG)
        }
    }
}

class ParamUseCase(postExecutionThread: PostExecutionThread) :
    FlowUseCase<String, String>(postExecutionThread) {

    override fun execute(params: String?): Flow<String> {
        requireParams(params)
        return flowOf(params)
    }
}

inline fun <reified T : Throwable> TestCoroutineScope.assertThrows(
    crossinline runnable: suspend () -> Unit
): T {
    val throwingRunnable = ThrowingRunnable {
        val job: Deferred<Unit> = async { runnable() }
        job.getCompletionExceptionOrNull()?.run { throw this }
        job.cancel()
    }
    return Assert.assertThrows(T::class.java, throwingRunnable)
}
