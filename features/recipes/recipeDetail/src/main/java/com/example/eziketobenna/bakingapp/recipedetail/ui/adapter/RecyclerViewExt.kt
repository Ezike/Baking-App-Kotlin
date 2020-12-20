package com.example.eziketobenna.bakingapp.recipedetail.ui.adapter

import com.example.eziketobenna.bakingapp.core.ext.safeOffer
import com.example.eziketobenna.bakingapp.recipedetail.model.StepDetailItem
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

val IngredientStepAdapter.stepClicks: Flow<StepDetailItem>
    get() = callbackFlow {
        val listener: StepClickListener = { step: StepDetailItem ->
            safeOffer(step)
            Unit
        }
        stepClickListener = listener
        awaitClose { stepClickListener = null }
    }.conflate()
