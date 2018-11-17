package com.example.eziketobenna.bakingapp.ui.details;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eziketobenna.bakingapp.R;
import com.example.eziketobenna.bakingapp.data.model.Ingredient;
import com.example.eziketobenna.bakingapp.data.model.Step;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int INGREDIENT = 0;
    private static final int STEP = 1;
    private boolean isTwoPane;
    private List<Object> mDataSet;

    public DetailAdapter(List<Object> mDataSet, boolean isTwoPane) {
        this.mDataSet = mDataSet;
        this.isTwoPane = isTwoPane;
    }

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
                View ingredientView = inflater.inflate(R.layout.step_list_content, viewGroup, false);
                holder = new IngredientViewHolder(ingredientView);
                break;
            case STEP:
                View stepView = inflater.inflate(R.layout.step_list_content, viewGroup, false);
                holder = new StepViewHolder(stepView);
                break;
            default:
                View view = inflater.inflate(R.layout.step_list_content, viewGroup, false);
                holder = new StepViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        switch (viewHolder.getItemViewType()) {
            case INGREDIENT:
                IngredientViewHolder ingredientViewHolder = (IngredientViewHolder) viewHolder;
                break;
            case STEP:
                StepViewHolder stepViewHolder = (StepViewHolder) viewHolder;
                break;
            default:
                StepViewHolder view = (StepViewHolder) viewHolder;
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet == null ? 0 : mDataSet.size();
    }

    class StepViewHolder extends RecyclerView.ViewHolder {
        StepViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {
        IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
