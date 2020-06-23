package com.example.eziketobenna.bakingapp.recipedetail.ui.adapter

import com.example.eziketobenna.bakingapp.recipedetail.model.StepDetailItem
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce

val IngredientStepAdapter.stepClicks: Flow<StepDetailItem>
    get() = callbackFlow {
        val listener: StepClickListener = { step: StepDetailItem ->
            offer(step)
            Unit
        }
        stepClickListener = listener
        awaitClose { stepClickListener = null }
    }.conflate().debounce(200)
