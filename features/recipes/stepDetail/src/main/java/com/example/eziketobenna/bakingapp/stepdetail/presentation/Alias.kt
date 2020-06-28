package com.example.eziketobenna.bakingapp.stepdetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer

typealias StepIntentProcessor =
    @JvmSuppressWildcards IntentProcessor<StepDetailViewIntent, StepDetailViewAction>

typealias StepActionProcessor =
    @JvmSuppressWildcards ActionProcessor<StepDetailViewAction, StepDetailViewResult>

typealias StepViewStateReducer =
    @JvmSuppressWildcards ViewStateReducer<StepDetailViewState, StepDetailViewResult>
