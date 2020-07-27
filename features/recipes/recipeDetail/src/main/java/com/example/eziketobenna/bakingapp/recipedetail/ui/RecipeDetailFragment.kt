package com.example.eziketobenna.bakingapp.recipedetail.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.eziketobenna.bakingapp.core.ext.actionBar
import com.example.eziketobenna.bakingapp.core.ext.observe
import com.example.eziketobenna.bakingapp.core.viewBinding.viewBinding
import com.example.eziketobenna.bakingapp.navigation.NavigationDispatcher
import com.example.eziketobenna.bakingapp.presentation.mvi.MVIView
import com.example.eziketobenna.bakingapp.recipedetail.R
import com.example.eziketobenna.bakingapp.recipedetail.databinding.FragmentRecipeDetailBinding
import com.example.eziketobenna.bakingapp.recipedetail.di.inject
import com.example.eziketobenna.bakingapp.recipedetail.presentation.RecipeDetailViewModel
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewIntent
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewIntent.LoadRecipeDetailIntent
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewIntent.OpenStepInfoViewIntent
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewState
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewState.NavigateToStepInfo
import com.example.eziketobenna.bakingapp.recipedetail.presentation.mvi.RecipeDetailViewState.Success
import com.example.eziketobenna.bakingapp.recipedetail.ui.adapter.IngredientStepAdapter
import com.example.eziketobenna.bakingapp.recipedetail.ui.adapter.stepClicks
import javax.inject.Inject
import javax.inject.Provider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import reactivecircus.flowbinding.lifecycle.events

class RecipeDetailFragment : Fragment(R.layout.fragment_recipe_detail),
    MVIView<RecipeDetailViewIntent, RecipeDetailViewState> {

    private val args: RecipeDetailFragmentArgs by navArgs()

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Provider<NavigationDispatcher>

    private val viewModel: RecipeDetailViewModel by viewModels { factory }

    @Inject
    lateinit var ingredientStepAdapter: IngredientStepAdapter

    private val binding: FragmentRecipeDetailBinding by viewBinding(FragmentRecipeDetailBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.processIntent(intents)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionBar?.title = args.recipe.name
        binding.detailRv.adapter = ingredientStepAdapter
        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    override fun render(state: RecipeDetailViewState) {
        when (state) {
            RecipeDetailViewState.Idle -> {
            }
            is Success -> ingredientStepAdapter.submitList(state.model)
            is NavigateToStepInfo ->
                state.openStepInfoEvent.consume(navigator.get()::openStepDetail)
        }
    }

    private val loadRecipeDetailIntent: Flow<LoadRecipeDetailIntent>
        get() = lifecycle.events().filter {
            /**
             * filtering by [Lifecycle.Event.ON_CREATE] didn't workout cos
             * this intent gets emitted always during onBackPress or config change,
             * and resets the [RecyclerView] scroll state
             */
            ingredientStepAdapter.isEmpty
        }.map { LoadRecipeDetailIntent(args.recipe) }

    private val openStepInfoIntent: Flow<OpenStepInfoViewIntent>
        get() = ingredientStepAdapter.stepClicks.map { stepDetailItem ->
            OpenStepInfoViewIntent(stepDetailItem, args.recipe.steps)
        }

    override val intents: Flow<RecipeDetailViewIntent>
        get() = merge(loadRecipeDetailIntent, openStepInfoIntent)
}
