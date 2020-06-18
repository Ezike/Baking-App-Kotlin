package com.example.eziketobenna.bakingapp.recipedetail.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.eziketobenna.bakingapp.recipedetail.databinding.ItemHeaderLayoutBinding
import com.example.eziketobenna.bakingapp.recipedetail.model.HeaderItem

class HeaderViewHolder(private val binding: ItemHeaderLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(headerItem: HeaderItem) {
        binding.header.run {
            text = context.getString(headerItem.header)
        }
    }
}
