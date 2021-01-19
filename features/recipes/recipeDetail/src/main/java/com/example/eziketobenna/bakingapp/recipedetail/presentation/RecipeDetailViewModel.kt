package com.example.eziketobenna.bakingapp.recipedetail.presentation

import androidx.lifecycle.ViewModel
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailStateMachine
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewIntent
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(
    private val detailStateMachine: RecipeDetailStateMachine
) : ViewModel() {

    val viewState: StateFlow<RecipeDetailViewState> = detailStateMachine.viewState

    fun processIntent(intents: RecipeDetailViewIntent) {
        detailStateMachine.processIntent(intents)
    }

    override fun onCleared() {
        detailStateMachine.unSubscribe()
        super.onCleared()
    }
}
