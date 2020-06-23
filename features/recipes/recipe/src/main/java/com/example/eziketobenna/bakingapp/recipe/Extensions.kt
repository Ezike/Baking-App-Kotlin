package com.example.eziketobenna.bakingapp.recipe

import com.example.eziketobenna.bakingapp.views.SimpleEmptyStateView
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce

//region FlowBinding extensions
val SimpleEmptyStateView.clicks: Flow<Unit>
    get() = callbackFlow {
        val listener: () -> Unit = {
            offer(Unit)
            Unit
        }
        buttonClickListener = listener
        awaitClose { buttonClickListener = null }
    }.conflate().debounce(200)
//endregion
