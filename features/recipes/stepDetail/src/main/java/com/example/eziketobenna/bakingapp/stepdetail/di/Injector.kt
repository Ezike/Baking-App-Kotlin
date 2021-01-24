package com.example.eziketobenna.bakingapp.stepdetail.di

import com.example.eziketobenna.bakingapp.core.di.component.CoreComponent
import com.example.eziketobenna.bakingapp.di.AppComponent
import com.example.eziketobenna.bakingapp.stepdetail.di.component.DaggerStepDetailComponent
import com.example.eziketobenna.bakingapp.stepdetail.ui.StepDetailFragment
import dagger.hilt.android.EntryPointAccessors

fun inject(fragment: StepDetailFragment) {
    DaggerStepDetailComponent
        .factory()
        .create(appComponent(fragment), coreComponent(fragment))
        .inject(fragment)
}

private fun appComponent(fragment: StepDetailFragment): AppComponent =
    EntryPointAccessors.fromActivity(
        fragment.requireActivity(),
        AppComponent::class.java
    )

private fun coreComponent(fragment: StepDetailFragment): CoreComponent =
    EntryPointAccessors.fromApplication(
        fragment.requireContext().applicationContext,
        CoreComponent::class.java
    )
