package com.example.eziketobenna.bakingapp.presentation.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan

abstract class StateMachine<A : ViewAction, I : ViewIntent, S : ViewState, out R : ViewResult>(
    private val actionProcessor: ActionProcessor<A, R>,
    private val intentProcessor: IntentProcessor<I, A>,
    private val reducer: ViewStateReducer<S, R>,
    initialAction: A,
    private val initialState: S
) {

    private val mainScope: CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val viewStateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)

    private val intentFlow: MutableSharedFlow<A> =
        MutableSharedFlow<A>(1).apply {
            tryEmit(initialAction)
        }

    fun processIntent(intent: I) {
        intentFlow.tryEmit(intentProcessor.intentToAction(intent))
    }

    val viewState: StateFlow<S>
        get() = viewStateFlow

    private fun makeState() {
        intentFlow
            .flatMapMerge(transform = actionProcessor::actionToResult)
            .scan(initialState, reducer::reduce)
            .onEach(viewStateFlow::emit)
            .launchIn(mainScope)
    }

    init {
        makeState()
    }

    fun unSubscribe() {
        mainScope.cancel()
    }
}
