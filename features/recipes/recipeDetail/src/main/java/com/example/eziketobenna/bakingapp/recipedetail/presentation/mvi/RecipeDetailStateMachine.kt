package com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi

import com.example.eziketobenna.bakingapp.model.RecipeModel
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
    @Assisted recipeModel: RecipeModel,
    @Assisted threader: ThreadUtil
) : StateMachine<RecipeDetailViewAction, RecipeDetailViewIntent,
    RecipeDetailViewState, RecipeDetailViewResult>(
    actionProcessor,
    intentProcessor,
    stateReducer,
    intentProcessor.intentToAction(
        RecipeDetailViewIntent.LoadRecipeDetailIntent(
            recipeModel
        )
    ),
    RecipeDetailViewState.Init(recipeModel.name),
    threader
) {

    @AssistedFactory
    interface Factory {
        fun create(recipeModel: RecipeModel, threader: ThreadUtil): RecipeDetailStateMachine
    }
}
