package com.example.eziketobenna.bakingapp.navigation

import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.model.StepInfoModel

interface NavigationDispatcher {

    fun openRecipeDetail(model: RecipeModel)
    fun openStepDetail(stepInfoModel: StepInfoModel)
    fun goBack()
}
