package com.example.eziketobenna.bakingapp.recipe

import com.example.eziketobenna.bakingapp.core.ext.safeOffer
import com.example.eziketobenna.bakingapp.views.SimpleEmptyStateView
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

//region FlowBinding extensions
val SimpleEmptyStateView.clicks: Flow<Unit>
    get() = callbackFlow {
        val listener: () -> Unit = {
            safeOffer(Unit)
            Unit
        }
        buttonClickListener = listener
        awaitClose { buttonClickListener = null }
    }.conflate()
//endregion
