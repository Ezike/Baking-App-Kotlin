package com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi

import com.example.eziketobenna.bakingapp.presentation.mvi.StateMachine
import com.example.eziketobenna.bakingapp.recipedetail.presentation.DetailActionProcessor
import com.example.eziketobenna.bakingapp.recipedetail.presentation.DetailIntentProcessor
import com.example.eziketobenna.bakingapp.recipedetail.presentation.DetailViewStateReducer
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class RecipeDetailStateMachine @AssistedInject constructor(
    actionProcessor: DetailActionProcessor,
    intentProcessor: DetailIntentProcessor,
    stateReducer: DetailViewStateReducer,
    @Assisted threader: ThreadUtil
) : StateMachine<RecipeDetailViewAction, RecipeDetailViewIntent,
    RecipeDetailViewState, RecipeDetailViewResult>(
    actionProcessor,
    intentProcessor,
    stateReducer,
    RecipeDetailViewAction.Idle,
    RecipeDetailViewState.Idle,
    threader
) {

    @AssistedFactory
    interface Factory {
        fun create(threader: ThreadUtil): RecipeDetailStateMachine
    }
}
