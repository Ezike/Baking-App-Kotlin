package com.example.eziketobenna.bakingapp.di

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.eziketobenna.bakingapp.R
import com.example.eziketobenna.bakingapp.navigation.NavigationDispatcher
import com.example.eziketobenna.bakingapp.navigation.NavigationDispatcherImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
object NavigationModule {

    @[Provides ActivityScoped]
    fun provideNavController(activity: Activity): NavController =
        activity.findNavController(R.id.mainNavHostFragment)
}

@InstallIn(ActivityComponent::class)
@Module
interface NavigationBindingModule {

    @get:[Binds ActivityScoped]
    val NavigationDispatcherImpl.navigationDispatcher: NavigationDispatcher
}
