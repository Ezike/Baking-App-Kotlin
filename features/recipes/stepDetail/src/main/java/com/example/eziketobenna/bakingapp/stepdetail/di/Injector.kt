package com.example.eziketobenna.bakingapp.stepdetail.di

import com.example.eziketobenna.bakingapp.di.AppComponent
import com.example.eziketobenna.bakingapp.stepdetail.di.component.DaggerStepDetailComponent
import com.example.eziketobenna.bakingapp.stepdetail.ui.StepDetailFragment
import dagger.hilt.android.EntryPointAccessors

fun inject(fragment: StepDetailFragment) {
    DaggerStepDetailComponent
        .factory()
        .create(appComponent(fragment))
        .inject(fragment)
}

private fun appComponent(fragment: StepDetailFragment): AppComponent =
    EntryPointAccessors.fromActivity(
        fragment.requireActivity(),
        AppComponent::class.java
    )
