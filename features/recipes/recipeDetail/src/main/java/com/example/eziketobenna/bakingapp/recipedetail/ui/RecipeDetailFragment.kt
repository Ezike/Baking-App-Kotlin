package com.example.eziketobenna.bakingapp.recipedetail.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.eziketobenna.bakingapp.core.ext.actionBar
import com.example.eziketobenna.bakingapp.core.factory.create
import com.example.eziketobenna.bakingapp.core.observe
import com.example.eziketobenna.bakingapp.core.viewBinding.viewBinding
import com.example.eziketobenna.bakingapp.navigation.NavigationDispatcher
import com.example.eziketobenna.bakingapp.presentation.mvi.MVIView
import com.example.eziketobenna.bakingapp.recipedetail.R
import com.example.eziketobenna.bakingapp.recipedetail.databinding.FragmentRecipeDetailBinding
import com.example.eziketobenna.bakingapp.recipedetail.di.inject
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewModel
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewIntent
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewIntent.OpenStepInfoViewIntent
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewState
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewState.NavigateToStepInfo
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewState.Success
import com.example.eziketobenna.bakingapp.recipedetail.ui.adapter.IngredientStepAdapter
import com.example.eziketobenna.bakingapp.recipedetail.ui.adapter.stepClicks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RecipeDetailFragment :
    Fragment(R.layout.fragment_recipe_detail),
    MVIView<RecipeDetailViewIntent, RecipeDetailViewState> {

    private val args: RecipeDetailFragmentArgs by navArgs()

    @Inject
    lateinit var factory: RecipeDetailViewModel.Factory

    @Inject
    lateinit var navigator: NavigationDispatcher

    private val viewModel: RecipeDetailViewModel by viewModels { create(factory, args.recipe) }

    @Inject
    lateinit var ingredientStepAdapter: IngredientStepAdapter

    private val binding: FragmentRecipeDetailBinding by viewBinding(FragmentRecipeDetailBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailRv.adapter = ingredientStepAdapter

        viewModel.viewState.observe(viewLifecycleOwner, ::render)

        openStepInfoIntent
            .onEach(viewModel::processIntent)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun render(state: RecipeDetailViewState) {
        actionBar?.title = state.toolbarTitle
        when (state) {
            is Success -> ingredientStepAdapter.submitList(state.model)
            is NavigateToStepInfo ->
                state.openStepInfoEvent.consume(navigator::openStepDetail)
            else -> return
        }
    }

    private val openStepInfoIntent: Flow<OpenStepInfoViewIntent>
        get() = ingredientStepAdapter.stepClicks.map { stepDetailItem ->
            OpenStepInfoViewIntent(stepDetailItem, args.recipe.steps)
        }
}
