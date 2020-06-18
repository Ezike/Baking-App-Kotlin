package com.example.eziketobenna.bakingapp.recipedetail.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.eziketobenna.bakingapp.recipedetail.databinding.IngredientListContentBinding
import com.example.eziketobenna.bakingapp.recipedetail.model.IngredientDetailItem

class IngredientViewHolder(private val binding: IngredientListContentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: IngredientDetailItem) {
        binding.ingredient.text = model.ingredient
    }
}
