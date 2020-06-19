package com.example.eziketobenna.bakingapp.recipe.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.eziketobenna.bakingapp.presentation.mvi.MVIView
import com.example.eziketobenna.bakingapp.recipe.R
import com.example.eziketobenna.bakingapp.recipe.clicks
import com.example.eziketobenna.bakingapp.recipe.databinding.FragmentRecipeBinding
import com.example.eziketobenna.bakingapp.recipe.getDrawable
import com.example.eziketobenna.bakingapp.recipe.inject
import com.example.eziketobenna.bakingapp.recipe.observe
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewIntent.RecipeRefreshViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewIntent.RecipeRetryViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewModel
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewState
import com.example.eziketobenna.bakingapp.views.viewBinding
import com.example.eziketobenna.bakkingapp.model.model.RecipeModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import reactivecircus.flowbinding.swiperefreshlayout.refreshes

class RecipeFragment : Fragment(R.layout.fragment_recipe),
    MVIView<RecipeViewIntent, RecipeViewState>, RecipeClickListener {

    @Inject
    lateinit var recipeAdapter: RecipeAdapter

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: RecipeViewModel by viewModels { factory }

    private val binding: FragmentRecipeBinding by viewBinding(FragmentRecipeBinding::bind)

    override fun invoke(model: RecipeModel) {
        findNavController().navigate(RecipeFragmentDirections.openRecipeDetail(model))
    }

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

        recipeAdapter.clickListener = this
        binding.mainRv.adapter = recipeAdapter

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private val emptyStateIntent: Flow<RecipeRetryViewIntent>
        get() = binding.emptyState.clicks
            .map {
                RecipeRetryViewIntent
            }

    private val swipeRefreshIntent: Flow<RecipeRefreshViewIntent>
        get() = binding.swipeRefresh.refreshes()
            .map {
                RecipeRefreshViewIntent
            }

    override val intents: Flow<RecipeViewIntent>
        get() = merge(swipeRefreshIntent, emptyStateIntent)

    override fun render(state: RecipeViewState) {
        when {
            state.isDataUnavailable -> renderEmptyState(state)
            state.isDataAvailableError -> renderDataAvailableErrorState(state)
            state.isNoDataError -> renderNoDataErrorState(state)
            state.isLoading -> renderLoadingState()
            state.isRefreshing -> renderRefreshState()
            else -> renderSuccessState(state)
        }
    }

    private fun renderEmptyState(state: RecipeViewState) {
        stopShimmer()
        binding.swipeRefresh.isRefreshing = false
        binding.swipeRefresh.isEnabled = true
        binding.emptyState.setImage(getDrawable(R.drawable.ic_empty))
        binding.emptyState.isVisible = state.recipes.isEmpty()
        binding.emptyState.setTitle(getString(R.string.no_data))
    }

    private fun renderRefreshState() {
        toggleSwipeRefresh(true)
        binding.emptyState.isVisible = false
        stopShimmer()
    }

    private fun renderLoadingState() {
        startShimmer()
        toggleSwipeRefresh(false)
        binding.emptyState.isVisible = false
    }

    private fun renderDataAvailableErrorState(state: RecipeViewState) {
        stopShimmer()
        recipeAdapter.submitList(state.recipes)
        binding.swipeRefresh.isRefreshing = false
        binding.swipeRefresh.isEnabled = true
        binding.emptyState.isVisible = false
        state.errorEvent?.consume { error ->
            Snackbar.make(binding.root, error, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun renderNoDataErrorState(state: RecipeViewState) {
        stopShimmer()
        binding.swipeRefresh.isRefreshing = false
        binding.swipeRefresh.isEnabled = true
        binding.emptyState.isVisible = true
        binding.emptyState.setImage(getDrawable(R.drawable.ic_error_page_2))
        binding.emptyState.setCaption(state.error)
        binding.emptyState.setTitle(getString(R.string.an_error_occurred))
    }

    private fun renderSuccessState(state: RecipeViewState) {
        recipeAdapter.submitList(state.recipes)
        stopShimmer()
        binding.swipeRefresh.isRefreshing = false
        binding.swipeRefresh.isEnabled = true
        binding.emptyState.isVisible = false
    }

    private fun toggleSwipeRefresh(boolean: Boolean) {
        binding.swipeRefresh.isRefreshing = boolean
        binding.swipeRefresh.isEnabled = boolean
        binding.emptyState.isVisible = false
    }

    private fun startShimmer() {
        binding.shimmer.isVisible = true
        binding.shimmer.startShimmer()
    }

    private fun stopShimmer() {
        binding.shimmer.stopShimmer()
        binding.shimmer.isVisible = false
    }
}
