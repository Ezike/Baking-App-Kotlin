package com.example.eziketobenna.bakingapp.presentation_android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eziketobenna.bakingapp.presentation.mvi.StateMachine

val ViewModel.stateMachineThreader get() = StateMachine.ThreadUtil(viewModelScope)