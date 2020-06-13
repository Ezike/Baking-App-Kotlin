package com.example.eziketobenna.bakingapp.core.executor

import com.example.eziketobenna.bakingapp.domain.executor.PostExecutionThread
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class PostExecutionThreadImpl @Inject constructor(
    override val main: CoroutineDispatcher,
    override val io: CoroutineDispatcher,
    override val default: CoroutineDispatcher
) : PostExecutionThread
