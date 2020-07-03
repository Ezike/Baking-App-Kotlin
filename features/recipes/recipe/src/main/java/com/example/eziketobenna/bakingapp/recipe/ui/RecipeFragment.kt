package com.example.eziketobenna.bakingapp.recipe.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.eziketobenna.bakingapp.core.ext.getDrawable
import com.example.eziketobenna.bakingapp.core.ext.observe
import com.example.eziketobenna.bakingapp.core.viewBinding.viewBinding
import com.example.eziketobenna.bakingapp.model.RecipeModel
import com.example.eziketobenna.bakingapp.presentation.mvi.MVIView
import com.example.eziketobenna.bakingapp.recipe.R
import com.example.eziketobenna.bakingapp.recipe.clicks
import com.example.eziketobenna.bakingapp.recipe.databinding.FragmentRecipeBinding
import com.example.eziketobenna.bakingapp.recipe.di.inject
import com.example.eziketobenna.bakingapp.recipe.presentation.RecipeViewModel
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent.RecipeRefreshViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewIntent.RecipeRetryViewIntent
import com.example.eziketobenna.bakingapp.recipe.presentation.mvi.RecipeViewState
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
        viewModel.openRecipeDetail(model)
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
            state.isDataUnavailable -> binding.renderEmptyState(state)
            state.isDataAvailableError -> binding.renderDataAvailableErrorState(state)
            state.isNoDataError -> binding.renderNoDataErrorState(state)
            state.isLoading -> binding.renderLoadingState()
            state.isRefreshing -> binding.renderRefreshState()
            else -> binding.renderSuccessState(state)
        }
    }

    private fun FragmentRecipeBinding.renderEmptyState(state: RecipeViewState) {
        stopShimmer()
        swipeRefresh.isRefreshing = false
        swipeRefresh.isEnabled = true
        emptyState.setImage(getDrawable(R.drawable.ic_empty))
        emptyState.isVisible = state.recipes.isEmpty()
        emptyState.setTitle(getString(R.string.no_data))
    }

    private fun FragmentRecipeBinding.renderRefreshState() {
        toggleSwipeRefresh(true)
        emptyState.isVisible = false
        stopShimmer()
    }

    private fun FragmentRecipeBinding.renderLoadingState() {
        startShimmer()
        toggleSwipeRefresh(false)
        emptyState.isVisible = false
    }

    private fun FragmentRecipeBinding.renderDataAvailableErrorState(state: RecipeViewState) {
        stopShimmer()
        recipeAdapter.submitList(state.recipes)
        swipeRefresh.isRefreshing = false
        swipeRefresh.isEnabled = true
        emptyState.isVisible = false
        state.errorEvent?.consume { error ->
            Snackbar.make(root, error, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun FragmentRecipeBinding.renderNoDataErrorState(state: RecipeViewState) {
        stopShimmer()
        swipeRefresh.isRefreshing = false
        swipeRefresh.isEnabled = true
        emptyState.isVisible = true
        emptyState.setImage(getDrawable(R.drawable.ic_error_page_2))
        emptyState.setCaption(state.error)
        emptyState.setTitle(getString(R.string.an_error_occurred))
    }

    private fun FragmentRecipeBinding.renderSuccessState(state: RecipeViewState) {
        recipeAdapter.submitList(state.recipes)
        stopShimmer()
        swipeRefresh.isRefreshing = false
        swipeRefresh.isEnabled = true
        emptyState.isVisible = false
    }

    private fun FragmentRecipeBinding.toggleSwipeRefresh(boolean: Boolean) {
        swipeRefresh.isRefreshing = boolean
        swipeRefresh.isEnabled = boolean
        emptyState.isVisible = false
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
