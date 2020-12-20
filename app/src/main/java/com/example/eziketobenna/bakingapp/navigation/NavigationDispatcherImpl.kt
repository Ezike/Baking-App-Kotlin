package com.example.eziketobenna.bakingapp.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.eziketobenna.bakingapp.R
import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.model.StepInfoModel
import javax.inject.Inject
import javax.inject.Provider

class NavigationDispatcherImpl @Inject constructor(
    private val navController: Provider<NavController>
) : NavigationDispatcher {

    override fun openRecipeDetail(model: RecipeModel) {
        navController.get().navigate(
            R.id.recipeDetailFragment,
            bundleOf(RECIPE_ARG to model)
        )
    }

    override fun openStepDetail(stepInfoModel: StepInfoModel) {
        navController.get().navigate(
            R.id.stepDetailFragment,
            bundleOf(STEP_INFO_ARG to stepInfoModel)
        )
    }

    override fun goBack() {
        navController.get().navigateUp()
    }

    companion object {
        const val RECIPE_ARG: String = "recipe"
        const val STEP_INFO_ARG: String = "stepInfo"
    }
}
