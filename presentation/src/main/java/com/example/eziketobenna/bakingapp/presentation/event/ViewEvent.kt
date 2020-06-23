package com.example.eziketobenna.bakingapp.presentation.event

data class ViewEvent<T>(val value: T) : SingleEvent<T>(value)
