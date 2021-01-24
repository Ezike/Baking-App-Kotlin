package com.example.eziketobenna.bakingapp.recipedetail.presentation

import androidx.lifecycle.ViewModel
import com.example.eziketobenna.bakingapp.domain.executor.PostExecutionThread
import com.example.eziketobenna.bakingapp.presentation_android.stateMachineThreader
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailStateMachine
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewIntent
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewState
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(
    private val detailStateMachineFactory: RecipeDetailStateMachine.Factory,
    thread: PostExecutionThread
) : ViewModel() {

    private val detailStateMachine =
        detailStateMachineFactory.create(stateMachineThreader)

    val viewState: StateFlow<RecipeDetailViewState> = detailStateMachine.viewState

    fun processIntent(intents: RecipeDetailViewIntent) {
        detailStateMachine.processIntent(intents)
    }
}
