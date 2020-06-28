package com.example.eziketobenna.bakingapp.di

import com.example.eziketobenna.bakingapp.navigation.NavigationDispatcher
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface AppComponent {
    val navigationDispatcher: NavigationDispatcher
}
