package com.example.eziketobenna.bakingapp.stepdetail.presentation

import com.example.eziketobenna.bakingapp.model.mapper.StepModelMapper
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewAction.GoToNextStepViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewAction.GoToPreviousStepViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewAction.LoadInitialViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewIntent.GoToNextStepViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewIntent.GoToPreviousStepViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewIntent.LoadInitialViewIntent
import javax.inject.Inject

class StepDetailIntentProcessor @Inject constructor(private val stepModelMapper: StepModelMapper) :
    IntentProcessor<StepDetailViewIntent, StepDetailViewAction> {

    override fun intentToAction(intent: StepDetailViewIntent): StepDetailViewAction {
        return when (intent) {
            is LoadInitialViewIntent -> loadInitialAction(intent)
            is GoToNextStepViewIntent -> goToNextStepViewAction(intent)
            is GoToPreviousStepViewIntent -> goToPreviousStepViewAction(intent)
        }
    }

    private fun goToNextStepViewAction(intent: GoToNextStepViewIntent): GoToNextStepViewAction {
        return GoToNextStepViewAction(stepModelMapper.mapToDomainList(intent.steps))
    }

    private fun goToPreviousStepViewAction(intent: GoToPreviousStepViewIntent): GoToPreviousStepViewAction {
        return GoToPreviousStepViewAction(stepModelMapper.mapToDomainList(intent.steps))
    }

    private fun loadInitialAction(intent: LoadInitialViewIntent): LoadInitialViewAction {
        return LoadInitialViewAction(
            intent.stepInfoModel.steps.indexOf(intent.stepInfoModel.step),
            stepModelMapper.mapToDomainList(intent.stepInfoModel.steps),
            stepModelMapper.mapToDomain(intent.stepInfoModel.step)
        )
    }
}
