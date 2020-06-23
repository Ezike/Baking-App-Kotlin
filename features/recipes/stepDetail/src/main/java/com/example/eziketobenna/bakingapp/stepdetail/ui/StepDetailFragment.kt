package com.example.eziketobenna.bakingapp.stepdetail.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.eziketobenna.bakingapp.core.ext.notEmpty
import com.example.eziketobenna.bakingapp.core.ext.observe
import com.example.eziketobenna.bakingapp.core.ext.onBackPress
import com.example.eziketobenna.bakingapp.core.viewBinding.viewBinding
import com.example.eziketobenna.bakingapp.presentation.mvi.MVIView
import com.example.eziketobenna.bakingapp.stepdetail.R
import com.example.eziketobenna.bakingapp.stepdetail.databinding.FragmentStepDetailBinding
import com.example.eziketobenna.bakingapp.stepdetail.di.inject
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewIntent.LoadInitialViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewModel
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewState
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import reactivecircus.flowbinding.lifecycle.events

class StepDetailFragment : Fragment(R.layout.fragment_step_detail),
    MVIView<StepDetailViewIntent, StepDetailViewState> {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: StepDetailViewModel by viewModels { factory }

    private val binding: FragmentStepDetailBinding by viewBinding(FragmentStepDetailBinding::bind)

    private val args: StepDetailFragmentArgs by navArgs()

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

        onBackPress {
            findNavController().navigateUp()
        }

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    override fun render(state: StepDetailViewState) {
        when (state) {
            StepDetailViewState.Idle -> {
            }
            is StepDetailViewState.Loaded -> renderLoadedState(state)
        }
    }

    private fun renderLoadedState(state: StepDetailViewState.Loaded) {
        binding.stepDetail.text = state.uiModel.stepDescription
        binding.stepId.text = state.uiModel.progressText
        binding.previousButton.isInvisible = !state.uiModel.showPrev
        binding.nextButton.isInvisible = !state.uiModel.showNext
        binding.videoPlayer.isVisible = state.uiModel.showVideo
        state.uiModel.videoUrl.notEmpty { url ->
            binding.videoPlayer.initVideoPlayer(this, url)
        }
    }

    private val loadStepInfoIntent: Flow<LoadInitialViewIntent>
        get() = lifecycle.events().filter {
            it == Lifecycle.Event.ON_CREATE
        }.map {
            LoadInitialViewIntent(args.stepInfo)
        }

    override val intents: Flow<StepDetailViewIntent>
        get() = merge(loadStepInfoIntent)
}
