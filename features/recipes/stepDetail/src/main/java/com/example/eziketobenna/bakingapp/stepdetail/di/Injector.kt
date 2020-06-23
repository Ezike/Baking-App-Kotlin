package com.example.eziketobenna.bakingapp.stepdetail.di

import com.example.eziketobenna.bakingapp.stepdetail.ui.StepDetailFragment

fun inject(fragment: StepDetailFragment) {
    DaggerStepDetailComponent
        .factory()
        .create()
        .inject(fragment)
}
