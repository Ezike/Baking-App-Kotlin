package com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi

import com.example.eziketobenna.bakingapp.presentation.mvi.StateMachine
import com.example.eziketobenna.bakingapp.recipedetail.presentation.DetailActionProcessor
import com.example.eziketobenna.bakingapp.recipedetail.presentation.DetailIntentProcessor
import com.example.eziketobenna.bakingapp.recipedetail.presentation.DetailViewStateReducer
import javax.inject.Inject

class RecipeDetailStateMachine @Inject constructor(
    actionProcessor: DetailActionProcessor,
    intentProcessor: DetailIntentProcessor,
    stateReducer: DetailViewStateReducer
) : StateMachine<RecipeDetailViewAction, RecipeDetailViewIntent,
    RecipeDetailViewState, RecipeDetailViewResult>(
    actionProcessor,
    intentProcessor,
    stateReducer,
    RecipeDetailViewAction.Idle,
    RecipeDetailViewState.Idle
)
