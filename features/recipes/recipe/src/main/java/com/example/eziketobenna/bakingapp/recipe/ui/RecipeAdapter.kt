package com.example.eziketobenna.bakingapp.recipe.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eziketobenna.bakingapp.core.imageLoader.ImageLoader
import com.example.eziketobenna.bakingapp.recipe.R
import com.example.eziketobenna.bakingapp.recipe.databinding.ContentMainBinding
import com.example.eziketobenna.bakingapp.recipe.inflate
import com.example.eziketobenna.bakingapp.recipe.notEmpty
import com.example.eziketobenna.bakkingapp.model.model.RecipeModel
import javax.inject.Inject

class RecipeAdapter @Inject constructor(private val imageLoader: ImageLoader) :
    ListAdapter<RecipeModel, RecipeAdapter.RecipeViewHolder>(diffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(ContentMainBinding.bind(parent.inflate(R.layout.content_main)))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position), imageLoader)
    }

    class RecipeViewHolder(private val binding: ContentMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: RecipeModel, imageLoader: ImageLoader) {
            binding.supportingText.text = model.name
            model.image.notEmpty { url ->
                imageLoader.loadImage(binding.recipeImage, url)
            }
        }
    }

    companion object {
        fun diffUtilCallback(): DiffUtil.ItemCallback<RecipeModel> =
            object : DiffUtil.ItemCallback<RecipeModel>() {
                override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
                    return oldItem === newItem && oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: RecipeModel,
                    newItem: RecipeModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
