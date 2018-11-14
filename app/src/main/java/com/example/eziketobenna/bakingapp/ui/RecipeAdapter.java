package com.example.eziketobenna.bakingapp.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.eziketobenna.bakingapp.R;
import com.example.eziketobenna.bakingapp.data.model.Recipe;
import com.example.eziketobenna.bakingapp.databinding.ContentMainBinding;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private List<Recipe> mRecipeList;
    private Context mContext;
    private LayoutInflater layoutInflater;

    public RecipeAdapter(List<Recipe> recipeList, Context context) {
        mRecipeList = recipeList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        ContentMainBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.content_main, viewGroup,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Recipe recipe = mRecipeList.get(i);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ContentMainBinding binding;

        public ViewHolder(@NonNull final ContentMainBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }

        void bind(Recipe recipe) {
            binding.setRecipe(recipe);
            binding.executePendingBindings();
        }
    }
}
