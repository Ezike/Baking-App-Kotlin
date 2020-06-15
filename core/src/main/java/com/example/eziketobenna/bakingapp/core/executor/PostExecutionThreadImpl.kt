package com.example.eziketobenna.bakingapp.core.executor

import com.example.eziketobenna.bakingapp.domain.executor.PostExecutionThread
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PostExecutionThreadImpl @Inject constructor() : PostExecutionThread {
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val default: CoroutineDispatcher = Dispatchers.Default
}
