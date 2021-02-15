package com.example.eziketobenna.bakingapp.presentation.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.stateIn

abstract class StateMachine<A : ViewAction, I : ViewIntent, S : ViewState, out R : ViewResult>(
    private val actionProcessor: ActionProcessor<A, R>,
    private val intentProcessor: IntentProcessor<I, A>,
    private val reducer: ViewStateReducer<S, R>,
    initialAction: A,
    initialState: S,
    threadUtil: ThreadUtil
) {

    private val intents = Channel<A>(
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    ).apply { offer(initialAction) }

    val viewState: StateFlow<S> = intents.receiveAsFlow()
        .flatMapMerge(transform = actionProcessor::actionToResult)
        .scan(initialState, reducer::reduce)
        .stateIn(threadUtil.mainScope, SharingStarted.Lazily, initialState)

    fun processIntent(intent: I) {
        intents.offer(intentProcessor.intentToAction(intent))
    }

    class ThreadUtil(val mainScope: CoroutineScope)
}
