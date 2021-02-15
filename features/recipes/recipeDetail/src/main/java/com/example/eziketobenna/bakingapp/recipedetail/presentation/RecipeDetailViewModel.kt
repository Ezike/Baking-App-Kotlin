package com.example.eziketobenna.bakingapp.recipedetail.presentation

import androidx.lifecycle.ViewModel
import com.example.eziketobenna.bakingapp.core.factory.AssistedViewModelFactory
import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.presentation_android.stateMachineThreader
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailStateMachine
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewIntent
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.StateFlow

class RecipeDetailViewModel @AssistedInject constructor(
    detailStateMachineFactory: RecipeDetailStateMachine.Factory,
    @Assisted recipeModel: RecipeModel
) : ViewModel() {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<RecipeModel> {
        override fun create(data: RecipeModel): RecipeDetailViewModel
    }

    private val detailStateMachine: RecipeDetailStateMachine =
        detailStateMachineFactory.create(recipeModel, stateMachineThreader)

    val viewState: StateFlow<RecipeDetailViewState> = detailStateMachine.viewState

    fun processIntent(intent: RecipeDetailViewIntent) {
        detailStateMachine.processIntent(intent)
    }
}
