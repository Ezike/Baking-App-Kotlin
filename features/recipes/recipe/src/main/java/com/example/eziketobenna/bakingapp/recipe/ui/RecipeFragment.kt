package com.example.eziketobenna.bakingapp.recipe.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.eziketobenna.bakingapp.common.viewBinding
import com.example.eziketobenna.bakingapp.core.factory.ViewModelFactory
import com.example.eziketobenna.bakingapp.recipe.databinding.FragmentRecipeBinding
import com.example.eziketobenna.bakingapp.recipe.injector
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewModel
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewState
import javax.inject.Inject

class RecipeFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    @Inject
    lateinit var recipeAdapter: RecipeAdapter

    private val binding: FragmentRecipeBinding by viewBinding(FragmentRecipeBinding::bind)

    private val viewModel: RecipeViewModel by viewModels { factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(this) { viewState ->
            when (viewState) {
                is RecipeViewState.Success -> recipeAdapter.submitList(viewState.recipes)
                is RecipeViewState.Error -> TODO()
                RecipeViewState.Initial -> TODO()
                RecipeViewState.Loading -> TODO()
                RecipeViewState.Refreshing -> TODO()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injector(this)
    }
}
