package com.example.eziketobenna.bakingapp.common_test

import android.os.Parcelable
import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.model.StepInfoModel
import com.example.eziketobenna.bakingapp.navigation.NavigationDispatcher
import kotlinx.coroutines.flow.MutableStateFlow

object FakeNavigationDispatcher : NavigationDispatcher {

    val event: MutableStateFlow<Parcelable?> = MutableStateFlow(null)

    override fun openRecipeDetail(model: RecipeModel) {
        event.value = model
    }

    override fun openStepDetail(stepInfoModel: StepInfoModel) {
        event.value = stepInfoModel
    }

    override fun goBack() {
        event.value = null
    }
}
