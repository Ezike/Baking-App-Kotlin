package com.example.eziketobenna.bakingapp.recipe.presentation.mvi

import com.example.eziketobenna.bakingapp.presentation.mvi.StateMachine
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeIntentProcessor
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeStateReducer
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewActionProcessor
import javax.inject.Inject

class HomeStateMachine @Inject constructor(
    actionProcessor: RecipeViewActionProcessor,
    intentProcessor: RecipeIntentProcessor,
    stateReducer: RecipeStateReducer
) : StateMachine<RecipeViewAction, RecipeViewIntent, RecipeViewState, RecipeViewResult>(
    actionProcessor,
    intentProcessor,
    stateReducer,
    RecipeViewAction.LoadInitialAction,
    RecipeViewState.init
)
