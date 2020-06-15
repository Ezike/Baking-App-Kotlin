package com.example.eziketobenna.bakingapp.recipe.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.eziketobenna.bakingapp.common.viewBinding
import com.example.eziketobenna.bakingapp.presentation.mvi.MVIView
import com.example.eziketobenna.bakingapp.recipe.R
import com.example.eziketobenna.bakingapp.recipe.databinding.FragmentRecipeBinding
import com.example.eziketobenna.bakingapp.recipe.injector
import com.example.eziketobenna.bakingapp.recipe.observe
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewModel
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewState
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge

class RecipeFragment : Fragment(R.layout.fragment_recipe),
    MVIView<RecipeViewIntent, RecipeViewState> {

    @Inject
    lateinit var recipeAdapter: RecipeAdapter

    @Inject
    lateinit var viewModel: RecipeViewModel

    private val binding: FragmentRecipeBinding by viewBinding(FragmentRecipeBinding::bind)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.processIntent(intents)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.processActions()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainRv.adapter = recipeAdapter
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector(this)
    }

    override fun render(state: RecipeViewState) {
        when (state) {
            is RecipeViewState.Success -> {
                recipeAdapter.submitList(state.recipes)
                binding.shimmer.stopShimmer()
                binding.shimmer.isVisible = false
            }
            is RecipeViewState.Error -> TODO()
            RecipeViewState.IDLE -> {
            }
            RecipeViewState.Loading -> {
                binding.shimmer.startShimmer()
            }
            RecipeViewState.Refreshing -> TODO()
        }
    }

    override val intents: Flow<RecipeViewIntent>
        get() = merge(flowOf(RecipeViewIntent.LoadInitial))
}
