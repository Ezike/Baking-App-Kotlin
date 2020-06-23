package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.model.StepInfoModel
import com.example.eziketobenna.bakingapp.model.mapper.StepModelMapper
import com.example.eziketobenna.bakingapp.presentation.event.ViewEvent
import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.recipedetail.R
import com.example.eziketobenna.bakingapp.recipedetail.model.HeaderItem
import com.example.eziketobenna.bakingapp.recipedetail.model.IngredientDetailMapper
import com.example.eziketobenna.bakingapp.recipedetail.model.RecipeDetailModel
import com.example.eziketobenna.bakingapp.recipedetail.model.StepDetailMapper
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailResult.LoadedData
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailResult.OpenStepInfo
import javax.inject.Inject

@OptIn(ExperimentalStdlibApi::class)
class RecipeDetailStateReducer @Inject constructor(
    private val stepDetailMapper: StepDetailMapper,
    private val ingredientDetailMapper: IngredientDetailMapper,
    private val stepModelMapper: StepModelMapper
) : ViewStateReducer<RecipeDetailViewState, RecipeDetailResult> {

    override fun reduce(
        previous: RecipeDetailViewState,
        result: RecipeDetailResult
    ): RecipeDetailViewState {

        return when (result) {
            RecipeDetailResult.IdleResult -> RecipeDetailViewState.Idle
            is LoadedData -> handleLoadDataResult(result)
            is OpenStepInfo -> navigateToStepInfo(result)
        }
    }

    private fun navigateToStepInfo(result: OpenStepInfo): RecipeDetailViewState.NavigateToStepInfo {
        val stepInfo =
            StepInfoModel(
                step = stepModelMapper.mapToModel(result.step),
                steps = stepModelMapper.mapToModelList(result.steps)
            )
        return RecipeDetailViewState.NavigateToStepInfo(ViewEvent(stepInfo))
    }

    private fun handleLoadDataResult(result: LoadedData): RecipeDetailViewState {
        return RecipeDetailViewState.Success(makeRecipeDetailModelList(result))
    }

    private fun makeRecipeDetailModelList(result: LoadedData): List<RecipeDetailModel> = buildList {
        add(HeaderItem(R.string.ingredients))
        addAll(ingredientDetailMapper.mapToModelList(result.ingredients))
        add(HeaderItem(R.string.steps))
        addAll(stepDetailMapper.mapToModelList(result.steps))
    }
}
