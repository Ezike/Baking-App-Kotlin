package com.example.eziketobenna.bakingapp.recipedetail.presentation

import com.example.eziketobenna.bakingapp.presentation.mvi.ViewStateReducer
import com.example.eziketobenna.bakingapp.recipedetail.R
import com.example.eziketobenna.bakingapp.recipedetail.model.HeaderItem
import com.example.eziketobenna.bakingapp.recipedetail.model.IngredientDetailMapper
import com.example.eziketobenna.bakingapp.recipedetail.model.RecipeDetailModel
import com.example.eziketobenna.bakingapp.recipedetail.model.StepDetailMapper
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailResult.LoadedData
import javax.inject.Inject

@OptIn(ExperimentalStdlibApi::class)
class RecipeDetailStateReducer @Inject constructor(
    private val stepDetailMapper: StepDetailMapper,
    private val ingredientDetailMapper: IngredientDetailMapper
) : ViewStateReducer<RecipeDetailViewState, RecipeDetailResult> {

    override fun reduce(
        previous: RecipeDetailViewState,
        result: RecipeDetailResult
    ): RecipeDetailViewState {

        return when (result) {
            RecipeDetailResult.IdleResult -> RecipeDetailViewState.Idle
            is LoadedData -> handleLoadDataResult(result)
        }
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
// class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private final StepListContentBinding stepBinding;
//
//        StepViewHolder(@NonNull View itemView) {
//            super(itemView);
//            stepBinding = DataBindingUtil.bind(itemView);
//            itemView.setOnClickListener(this);
//        }
//
//        void bind(Step step) {
//            stepBinding.setStep(step);
//            stepBinding.executePendingBindings();
//        }
//
//        @Override
//        public void onClick(View v) {
//            int position = getAdapterPosition();
//            Step step = (Step) mDataSet.get(position);
//            mListener.onStepClick(step);
//        }
//    }
//
//    class IngredientViewHolder extends RecyclerView.ViewHolder {
//        private final IngredientListContentBinding ingredientBinding;
//
//        IngredientViewHolder(@NonNull View itemView) {
//            super(itemView);
//            ingredientBinding = DataBindingUtil.bind(itemView);
//        }
//
//        void bind(Ingredient ingredient) {
//            ingredientBinding.setIngredient(ingredient);
//            ingredientBinding.executePendingBindings();
//        }
//    }
