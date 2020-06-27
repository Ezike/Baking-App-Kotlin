package com.example.eziketobenna.bakingapp.stepdetail.di.module

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepActionProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewStateReducer
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepIntentProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepViewStateReducer
import com.example.eziketobenna.bakingapp.stepdetail.presentation.processor.StepDetailActionProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.processor.StepDetailIntentProcessor
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
interface PresentationModule {

    @get:[Binds FeatureScope]
    val StepDetailActionProcessor.actionProcessor: StepActionProcessor

    @get:[Binds FeatureScope]
    val StepDetailIntentProcessor.intentProcessor: StepIntentProcessor

    @get:[Binds FeatureScope]
    val StepDetailViewStateReducer.reducer: StepViewStateReducer
}
