package com.example.eziketobenna.bakingapp.recipedetail.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.eziketobenna.bakingapp.recipedetail.databinding.StepListContentBinding
import com.example.eziketobenna.bakingapp.recipedetail.model.StepDetailItem

class StepViewHolder(private val binding: StepListContentBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        stepDetailItem: StepDetailItem,
        stepClickListener: StepClickListener?
    ) {
        binding.shortDescription.text = stepDetailItem.shortDescription
        binding.root.setOnClickListener {
            stepClickListener?.invoke(stepDetailItem)
        }
    }
}
