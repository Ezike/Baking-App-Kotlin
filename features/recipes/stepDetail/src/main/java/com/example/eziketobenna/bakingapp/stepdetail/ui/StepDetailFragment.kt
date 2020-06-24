package com.example.eziketobenna.bakingapp.stepdetail.ui

import android.content.Context
import android.content.res.Configuration
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
import com.example.eziketobenna.bakingapp.core.ext.actionBar
import com.example.eziketobenna.bakingapp.core.ext.notEmpty
import com.example.eziketobenna.bakingapp.core.ext.observe
import com.example.eziketobenna.bakingapp.core.ext.onBackPress
import com.example.eziketobenna.bakingapp.core.ext.visible
import com.example.eziketobenna.bakingapp.core.viewBinding.viewBinding
import com.example.eziketobenna.bakingapp.presentation.mvi.MVIView
import com.example.eziketobenna.bakingapp.stepdetail.R
import com.example.eziketobenna.bakingapp.stepdetail.databinding.FragmentStepDetailBinding
import com.example.eziketobenna.bakingapp.stepdetail.di.inject
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewIntent.LoadInitialViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewModel
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewState
import com.example.eziketobenna.bakingapp.videoplayer.VideoPlayerState
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

    private var playerState: VideoPlayerState = VideoPlayerState()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.processIntent(intents)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(PLAYER_STATE_KEY, playerState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkScreenOrientation()

        onBackPress {
            findNavController().navigateUp()
        }

        savedInstanceState?.getParcelable<VideoPlayerState>(PLAYER_STATE_KEY)?.let { state ->
            playerState = state
        }

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun checkScreenOrientation() {
        val orientation: Int = resources.configuration.orientation
        binding.videoPlayer.isFullScreen = orientation == Configuration.ORIENTATION_LANDSCAPE
        binding.navigationLayout.isVisible = orientation != Configuration.ORIENTATION_LANDSCAPE
        actionBar?.visible = orientation != Configuration.ORIENTATION_LANDSCAPE
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
        binding.nextButton.text = getNextButtonText(state)
        binding.videoPlayer.isVisible = state.uiModel.showVideo
        state.uiModel.videoUrl.notEmpty { url ->
            playerState.videoUrl = url
            binding.videoPlayer.init(this, playerState)
        }
    }

    private fun getNextButtonText(state: StepDetailViewState.Loaded): String =
        if (state.uiModel.showNext) getString(R.string.next) else getString(R.string.finish)

    private val loadStepInfoIntent: Flow<LoadInitialViewIntent>
        get() = lifecycle.events().filter {
            it == Lifecycle.Event.ON_CREATE
        }.map {
            LoadInitialViewIntent(args.stepInfo)
        }

    override val intents: Flow<StepDetailViewIntent>
        get() = merge(loadStepInfoIntent)

    companion object {
        const val PLAYER_STATE_KEY: String = "p"
    }
}
