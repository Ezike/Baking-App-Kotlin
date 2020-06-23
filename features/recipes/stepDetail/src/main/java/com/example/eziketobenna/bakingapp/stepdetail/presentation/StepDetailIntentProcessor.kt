package com.example.eziketobenna.bakingapp.stepdetail.presentation

import com.example.eziketobenna.bakingapp.model.mapper.StepModelMapper
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewAction.LoadInitialViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewIntent.LoadInitialViewIntent
import javax.inject.Inject

class StepDetailIntentProcessor @Inject constructor(private val stepModelMapper: StepModelMapper) :
    IntentProcessor<StepDetailViewIntent, StepDetailViewAction> {

    override fun intentToAction(intent: StepDetailViewIntent): StepDetailViewAction {
        return when (intent) {
            is LoadInitialViewIntent -> loadInitialAction(intent)
        }
    }

    private fun loadInitialAction(intent: LoadInitialViewIntent): LoadInitialViewAction {
        return LoadInitialViewAction(
            intent.stepInfoModel.steps.indexOf(intent.stepInfoModel.step) + 1,
            stepModelMapper.mapToDomainList(intent.stepInfoModel.steps),
            stepModelMapper.mapToDomain(intent.stepInfoModel.step)
        )
    }
}
