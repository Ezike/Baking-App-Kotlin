package com.example.eziketobenna.bakingapp.ui.details;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eziketobenna.bakingapp.R;
import com.example.eziketobenna.bakingapp.data.model.Ingredient;
import com.example.eziketobenna.bakingapp.data.model.Step;
import com.example.eziketobenna.bakingapp.databinding.IngredientListContentBinding;
import com.example.eziketobenna.bakingapp.databinding.StepListContentBinding;

import java.util.List;

/**
 * RecyclerView Adapter class that displays steps and ingredients
 * the Adapter takes two ViewHolder classes
 */
public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int INGREDIENT = 0;
    private static final int STEP = 1;
    private List<Object> mDataSet;
    private StepClickListener mListener;

    DetailAdapter(List<Object> mDataSet, StepClickListener listener) {
        this.mDataSet = mDataSet;
        mListener = listener;
    }

    // check the type of object to be returned
    @Override
    public int getItemViewType(int position) {
        if (mDataSet.get(position) instanceof Ingredient) {
            return INGREDIENT;
        } else if (mDataSet.get(position) instanceof Step) {
            return STEP;
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (i) {
            case INGREDIENT:
                IngredientListContentBinding ingredientListContentBinding = DataBindingUtil
                        .inflate(inflater, R.layout.ingredient_list_content, viewGroup, false);
                holder = new IngredientViewHolder(ingredientListContentBinding.getRoot());
                break;
            case STEP:
                StepListContentBinding stepListContentBinding = DataBindingUtil
                        .inflate(inflater, R.layout.step_list_content, viewGroup, false);
                holder = new StepViewHolder(stepListContentBinding.getRoot());
                break;
            default:
                stepListContentBinding = DataBindingUtil
                        .inflate(inflater, R.layout.step_list_content, viewGroup, false);
                holder = new StepViewHolder(stepListContentBinding.getRoot());
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        switch (viewHolder.getItemViewType()) {
            case INGREDIENT:
                IngredientViewHolder ingredientViewHolder = (IngredientViewHolder) viewHolder;
                Ingredient ingredient = (Ingredient) mDataSet.get(i);
                if (ingredient != null) {
                    ingredientViewHolder.bind(ingredient);
                }
                break;
            case STEP:
                StepViewHolder stepViewHolder = (StepViewHolder) viewHolder;
                Step step = (Step) mDataSet.get(i);
                if (step != null) {
                    stepViewHolder.bind(step);
                }
                break;
            default:
                StepViewHolder view = (StepViewHolder) viewHolder;
                step = (Step) mDataSet.get(i);
                if (step != null) {
                    view.bind(step);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet == null ? 0 : mDataSet.size();
    }

    public interface StepClickListener {
        void onStepClick(Step step);
    }

    class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final StepListContentBinding stepBinding;

        StepViewHolder(@NonNull View itemView) {
            super(itemView);
            stepBinding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        void bind(Step step) {
            stepBinding.setStep(step);
            stepBinding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Step step = (Step) mDataSet.get(position);
            mListener.onStepClick(step);
        }
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {
        private final IngredientListContentBinding ingredientBinding;

        IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientBinding = DataBindingUtil.bind(itemView);
        }

        void bind(Ingredient ingredient) {
            ingredientBinding.setIngredient(ingredient);
            ingredientBinding.executePendingBindings();
        }
    }
}
