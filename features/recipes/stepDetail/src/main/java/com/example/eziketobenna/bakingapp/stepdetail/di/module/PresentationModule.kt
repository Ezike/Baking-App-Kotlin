package com.example.eziketobenna.bakingapp.stepdetail.di.module

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailActionProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailIntentProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewState
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewStateReducer
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck

@DisableInstallInCheck
@Module
interface PresentationModule {

    @get:[Binds FeatureScope]
    val StepDetailActionProcessor.actionProcessor: ActionProcessor<StepDetailViewAction, StepDetailViewResult>

    @get:[Binds FeatureScope]
    val StepDetailIntentProcessor.intentProcessor: IntentProcessor<StepDetailViewIntent, StepDetailViewAction>

    @get:[Binds FeatureScope]
    val StepDetailViewStateReducer.reducer: ViewStateReducer<StepDetailViewState, StepDetailViewResult>
}
