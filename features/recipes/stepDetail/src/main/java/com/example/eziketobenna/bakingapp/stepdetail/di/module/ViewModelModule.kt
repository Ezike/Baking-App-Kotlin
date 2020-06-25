package com.example.eziketobenna.bakingapp.stepdetail.di.module

import androidx.lifecycle.ViewModel
import com.example.eziketobenna.bakingapp.core.di.mapkeys.ViewModelKey
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap

@DisableInstallInCheck
@Module
interface ViewModelModule {

    @get:[Binds IntoMap ViewModelKey(StepDetailViewModel::class)]
    val StepDetailViewModel.stepDetailViewModel: ViewModel
}
