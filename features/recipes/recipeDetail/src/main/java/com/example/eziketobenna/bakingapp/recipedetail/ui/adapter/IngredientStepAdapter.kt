package com.example.eziketobenna.bakingapp.recipedetail.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.eziketobenna.bakingapp.core.ext.inflate
import com.example.eziketobenna.bakingapp.recipedetail.R
import com.example.eziketobenna.bakingapp.recipedetail.databinding.IngredientListContentBinding
import com.example.eziketobenna.bakingapp.recipedetail.databinding.ItemHeaderLayoutBinding
import com.example.eziketobenna.bakingapp.recipedetail.databinding.StepListContentBinding
import com.example.eziketobenna.bakingapp.recipedetail.model.HeaderItem
import com.example.eziketobenna.bakingapp.recipedetail.model.IngredientDetailItem
import com.example.eziketobenna.bakingapp.recipedetail.model.RecipeDetailModel
import com.example.eziketobenna.bakingapp.recipedetail.model.StepDetailItem
import javax.inject.Inject

typealias StepClickListener = (StepDetailItem) -> Unit

class IngredientStepAdapter @Inject constructor() :
    ListAdapter<RecipeDetailModel, RecyclerView.ViewHolder>(diffUtilCallback) {

    var stepClickListener: StepClickListener? = null

    val isEmpty: Boolean
        get() = itemCount == 0

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is IngredientDetailItem -> R.layout.ingredient_list_content
            is StepDetailItem -> R.layout.step_list_content
            is HeaderItem -> R.layout.item_header_layout
            else -> throw IllegalArgumentException("Unknown view type at position: $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.ingredient_list_content -> IngredientViewHolder(
                IngredientListContentBinding.bind(
                    parent.inflate(R.layout.ingredient_list_content)
                )
            )
            R.layout.step_list_content -> StepViewHolder(
                StepListContentBinding.bind(
                    parent.inflate(
                        R.layout.step_list_content
                    )
                )
            )
            R.layout.item_header_layout -> HeaderViewHolder(
                ItemHeaderLayoutBinding.bind(
                    parent.inflate(
                        R.layout.item_header_layout
                    )
                )
            )
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is IngredientViewHolder -> holder.bind(getItem(position) as IngredientDetailItem)
            is StepViewHolder -> holder.bind(getItem(position) as StepDetailItem, stepClickListener)
            is HeaderViewHolder -> holder.bind(getItem(position) as HeaderItem)
        }
    }

    companion object {
        val diffUtilCallback: DiffUtil.ItemCallback<RecipeDetailModel>
            get() = object : DiffUtil.ItemCallback<RecipeDetailModel>() {
                override fun areItemsTheSame(
                    oldItem: RecipeDetailModel,
                    newItem: RecipeDetailModel
                ): Boolean {
                    return when (oldItem) {
                        is IngredientDetailItem -> {
                            newItem is IngredientDetailItem && oldItem === newItem
                        }
                        is StepDetailItem -> {
                            newItem is StepDetailItem && oldItem === newItem
                        }
                        is HeaderItem -> {
                            newItem is StepDetailItem && oldItem === newItem
                        }
                    }
                }

                override fun areContentsTheSame(
                    oldItem: RecipeDetailModel,
                    newItem: RecipeDetailModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
