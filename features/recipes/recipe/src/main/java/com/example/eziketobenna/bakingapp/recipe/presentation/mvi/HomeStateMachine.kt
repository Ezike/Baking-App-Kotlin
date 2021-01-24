package com.example.eziketobenna.bakingapp.recipe.presentation.mvi

import com.example.eziketobenna.bakingapp.presentation.mvi.StateMachine
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeIntentProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeStateReducer
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewActionProcessor
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class HomeStateMachine @AssistedInject constructor(
    actionProcessor: RecipeViewActionProcessor,
    intentProcessor: RecipeIntentProcessor,
    stateReducer: RecipeStateReducer,
    @Assisted threader: ThreadUtil
) : StateMachine<RecipeViewAction, RecipeViewIntent, RecipeViewState, RecipeViewResult>(
    actionProcessor,
    intentProcessor,
    stateReducer,
    RecipeViewAction.LoadInitialAction,
    RecipeViewState.init,
    threader
) {

    @AssistedFactory
    interface Factory {
        fun create(threader: ThreadUtil): HomeStateMachine
    }
}
