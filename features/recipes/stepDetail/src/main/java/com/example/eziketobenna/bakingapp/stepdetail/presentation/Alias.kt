package com.example.eziketobenna.bakingapp.stepdetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ActionProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewResult
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewState

typealias StepIntentProcessor =
    @JvmSuppressWildcards IntentProcessor<StepDetailViewIntent, StepDetailViewAction>

typealias StepActionProcessor =
    @JvmSuppressWildcards ActionProcessor<StepDetailViewAction, StepDetailViewResult>

typealias StepViewStateReducer =
    @JvmSuppressWildcards ViewStateReducer<StepDetailViewState, StepDetailViewResult>
