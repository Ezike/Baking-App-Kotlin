package com.example.eziketobenna.bakingapp.ui.recipes;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eziketobenna.bakingapp.R;
import com.example.eziketobenna.bakingapp.data.model.Recipe;
import com.example.eziketobenna.bakingapp.databinding.ContentMainBinding;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private List<Recipe> mRecipeList;
    private RecipeClickListener mListener;

    RecipeAdapter(RecipeClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ContentMainBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.content_main, viewGroup,
                false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Recipe recipe = mRecipeList.get(i);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return mRecipeList == null ? 0 : mRecipeList.size();
    }


    public void setRecipes(List<Recipe> recipes) {
        mRecipeList = recipes;
        notifyDataSetChanged();
    }

    public interface RecipeClickListener {
        void onRecipeClick(Recipe recipe);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ContentMainBinding binding;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(this);
        }

        void bind(Recipe recipe) {
            binding.setRecipe(recipe);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Recipe recipe = mRecipeList.get(position);
            mListener.onRecipeClick(recipe);
        }
    }
}
