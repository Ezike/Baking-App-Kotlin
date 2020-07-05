package com.example.eziketobenna.bakingapp.recipe.presentation.fake

import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.model.StepInfoModel
import com.example.eziketobenna.bakingapp.navigation.NavigationDispatcher

val navigationDispatcher: NavigationDispatcher = object : NavigationDispatcher {
    override fun openRecipeDetail(model: RecipeModel) {}

    override fun openStepDetail(stepInfoModel: StepInfoModel) {}

    override fun goBack() {}
}
