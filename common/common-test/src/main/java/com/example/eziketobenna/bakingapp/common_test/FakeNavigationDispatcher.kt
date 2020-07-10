package com.example.eziketobenna.bakingapp.common_test

import android.os.Parcelable
import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.model.StepInfoModel
import com.example.eziketobenna.bakingapp.navigation.NavigationDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object FakeNavigationDispatcher : NavigationDispatcher {

    private val mutableEvent: MutableStateFlow<Parcelable?> = MutableStateFlow(null)
    val event: StateFlow<Parcelable?> get() = mutableEvent

    override fun openRecipeDetail(model: RecipeModel) {
        mutableEvent.value = model
    }

    override fun openStepDetail(stepInfoModel: StepInfoModel) {
        mutableEvent.value = stepInfoModel
    }

    override fun goBack() {
        mutableEvent.value = null
    }
}
