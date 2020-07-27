package com.example.eziketobenna.bakingapp.stepdetail.presentation.processor

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.model.mapper.StepModelMapper
import com.example.eziketobenna.bakingapp.presentation.mvi.IntentProcessor
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewAction.GoToNextStepViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewAction.GoToPreviousStepViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewAction.LoadInitialViewAction
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewIntent.GoToNextStepViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewIntent.GoToPreviousStepViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewIntent.LoadInitialViewIntent
import javax.inject.Inject

@FeatureScope
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
