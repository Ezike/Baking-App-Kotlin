package com.example.eziketobenna.bakingapp.presentation.event

data class ViewEvent<T>(val t: T) : SingleEvent<T>(t)
