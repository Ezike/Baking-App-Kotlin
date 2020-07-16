package com.example.eziketobenna.bakingapp.core.di.module

import com.example.eziketobenna.bakingapp.core.executor.PostExecutionThreadImpl
import com.example.eziketobenna.bakingapp.domain.executor.PostExecutionThread
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface ExecutorModule {

    @get:[Binds Singleton]
    val PostExecutionThreadImpl.postExecutionThread: PostExecutionThread
}
