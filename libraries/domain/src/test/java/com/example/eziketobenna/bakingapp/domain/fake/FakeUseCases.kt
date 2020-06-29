package com.example.eziketobenna.bakingapp.domain.fake

import com.example.eziketobenna.bakingapp.domain.exception.requireParams
import com.example.eziketobenna.bakingapp.domain.executor.PostExecutionThread
import com.example.eziketobenna.bakingapp.domain.usecase.base.FlowUseCase
import java.net.SocketTimeoutException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ExceptionUseCase(postExecutionThread: PostExecutionThread) :
    FlowUseCase<Unit, Unit>(postExecutionThread) {

    override fun execute(params: Unit?): Flow<Unit> {
        throw SocketTimeoutException("No network")
    }
}

class ParamUseCase(postExecutionThread: PostExecutionThread) :
    FlowUseCase<String, String>(postExecutionThread) {

    override fun execute(params: String?): Flow<String> {
        requireParams(params)
        return flowOf(params)
    }
}
