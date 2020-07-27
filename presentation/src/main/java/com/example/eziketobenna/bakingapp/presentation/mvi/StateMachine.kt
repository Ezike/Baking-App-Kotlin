package com.example.eziketobenna.bakingapp.presentation.mvi

import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

abstract class StateMachine<A : ViewAction, I : ViewIntent, S : ViewState, out R : ViewResult>(
    private val actionProcessor: ActionProcessor<A, R>,
    private val intentProcessor: IntentProcessor<I, A>,
    private val reducer: ViewStateReducer<S, R>,
    initialAction: A,
    initialState: S
) {

    private val viewStateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)

    private val intentsChannel: ConflatedBroadcastChannel<A> =
        ConflatedBroadcastChannel(initialAction)

    fun processIntents(intents: Flow<I>): Flow<I> = intents.onEach { viewIntents ->
        intentsChannel.offer(intentProcessor.intentToAction(viewIntents))
    }

    val viewState: StateFlow<S>
        get() = viewStateFlow

    val processor: Flow<S> = intentsChannel.asFlow()
        .flatMapMerge { action ->
            actionProcessor.actionToResult(action)
        }.scan(initialState) { previous, result ->
            reducer.reduce(previous, result)
        }.distinctUntilChanged()
        .onEach { recipeViewState ->
            viewStateFlow.value = recipeViewState
        }
}
