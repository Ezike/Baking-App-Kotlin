package com.example.eziketobenna.bakingapp.domain.usecase.base

import com.example.eziketobenna.bakingapp.domain.executor.PostExecutionThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, out T> constructor(
    private val postExecutionThread: PostExecutionThread
) {

    /**
     * Function which builds Flow instance based on given arguments
     * @param params initial use case arguments
     */
    abstract operator fun invoke(params: Params? = null): Flow<T>

    protected fun execute(params: Params? = null): Flow<T> {
        return this(params)
                .flowOn(postExecutionThread.io)
    }
}
