package com.example.eziketobenna.bakingapp.recipedetail.ui.adapter

import com.example.eziketobenna.bakingapp.recipedetail.model.StepDetailItem
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce

val IngredientStepAdapter.stepClicks: Flow<Pair<StepDetailItem, Int>>
    get() = callbackFlow {
        val listener: StepClickListener = { step: StepDetailItem, index: Int ->
            offer(Pair(step, index))
            Unit
        }
        stepClickListener = listener
        awaitClose { stepClickListener = null }
    }.conflate().debounce(200)
