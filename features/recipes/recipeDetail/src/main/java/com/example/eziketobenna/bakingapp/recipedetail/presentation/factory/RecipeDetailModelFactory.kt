package com.example.eziketobenna.bakingapp.recipedetail.presentation.factory

import com.example.eziketobenna.bakingapp.core.di.scope.FeatureScope
import com.example.eziketobenna.bakingapp.domain.model.Ingredient
import com.example.eziketobenna.bakingapp.domain.model.Step
import com.example.eziketobenna.bakingapp.model.StepInfoModel
import com.example.eziketobenna.bakingapp.model.mapper.StepModelMapper
import com.example.eziketobenna.bakingapp.recipedetail.R
import com.example.eziketobenna.bakingapp.recipedetail.model.HeaderItem
import com.example.eziketobenna.bakingapp.recipedetail.model.IngredientDetailMapper
import com.example.eziketobenna.bakingapp.recipedetail.model.RecipeDetailModel
import com.example.eziketobenna.bakingapp.recipedetail.model.StepDetailMapper
import javax.inject.Inject

@[FeatureScope OptIn(ExperimentalStdlibApi::class)]
class RecipeDetailModelFactory @Inject constructor(
    private val stepDetailMapper: StepDetailMapper,
    private val ingredientDetailMapper: IngredientDetailMapper,
    private val stepModelMapper: StepModelMapper
) {

    fun createStepInfoModel(step: Step, steps: List<Step>): StepInfoModel = StepInfoModel(
        step = stepModelMapper.mapToModel(step),
        steps = stepModelMapper.mapToModelList(steps)
    )

    fun makeRecipeDetailModelList(
        ingredients: List<Ingredient>,
        steps: List<Step>
    ): List<RecipeDetailModel> = buildList {
        add(HeaderItem(R.string.ingredients))
        addAll(ingredientDetailMapper.mapToModelList(ingredients))
        add(HeaderItem(R.string.steps))
        addAll(stepDetailMapper.mapToModelList(steps))
    }
}
