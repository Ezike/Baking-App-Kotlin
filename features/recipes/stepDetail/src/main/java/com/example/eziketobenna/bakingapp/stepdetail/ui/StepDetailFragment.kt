package com.example.eziketobenna.bakingapp.stepdetail.ui

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.eziketobenna.bakingapp.core.ext.actionBar
import com.example.eziketobenna.bakingapp.core.ext.visible
import com.example.eziketobenna.bakingapp.core.factory.create
import com.example.eziketobenna.bakingapp.core.observe
import com.example.eziketobenna.bakingapp.core.viewBinding.viewBinding
import com.example.eziketobenna.bakingapp.navigation.NavigationDispatcher
import com.example.eziketobenna.bakingapp.presentation.mvi.MVIView
import com.example.eziketobenna.bakingapp.stepdetail.R
import com.example.eziketobenna.bakingapp.stepdetail.databinding.FragmentStepDetailBinding
import com.example.eziketobenna.bakingapp.stepdetail.di.inject
import com.example.eziketobenna.bakingapp.stepdetail.presentation.StepDetailViewModel
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewIntent.GoToNextStepViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewIntent.GoToPreviousStepViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewIntent.LoadInitialViewIntent
import com.example.eziketobenna.bakingapp.stepdetail.presentation.mvi.StepDetailViewState
import com.example.eziketobenna.bakingapp.videoplayer.VideoPlayerState
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.view.clicks
import javax.inject.Inject
import javax.inject.Provider

class StepDetailFragment :
    Fragment(R.layout.fragment_step_detail),
    MVIView<StepDetailViewIntent, StepDetailViewState> {

    @Inject
    lateinit var factory: StepDetailViewModel.Factory

    @Inject
    lateinit var navigator: NavigationDispatcher

    private val viewModel: StepDetailViewModel by viewModels { create(factory, args.stepInfo) }

    private val binding: FragmentStepDetailBinding by viewBinding(FragmentStepDetailBinding::bind)

    private val args: StepDetailFragmentArgs by navArgs()

    private var playerState: VideoPlayerState = VideoPlayerState()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(PLAYER_STATE_KEY, playerState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkScreenOrientation()

        savedInstanceState?.getParcelable<VideoPlayerState>(PLAYER_STATE_KEY)
            ?.let { state ->
                playerState = state
            }

        merge(
            gotoNextStepIntent,
            gotoPreviousStepIntent
        ).onEach(viewModel::processIntent).launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun checkScreenOrientation() {
        val orientation: Int = resources.configuration.orientation
        binding.videoPlayer.isFullScreen = orientation == Configuration.ORIENTATION_LANDSCAPE
        binding.navigationLayout.isVisible = orientation != Configuration.ORIENTATION_LANDSCAPE
        actionBar?.visible = orientation != Configuration.ORIENTATION_LANDSCAPE
    }

    override fun render(state: StepDetailViewState) {
        actionBar?.title = state.toolbarTitle
        when (state) {
            is StepDetailViewState.Loaded -> renderLoadedState(state)
            is StepDetailViewState.FinishEvent -> state.closeEvent.consume {
                navigator.goBack()
            }
            else -> return
        }
    }

    private fun renderLoadedState(state: StepDetailViewState.Loaded) {
        binding.stepDetail.text = state.stepDescription
        binding.stepId.text = state.progressText
        binding.previousButton.isInvisible = !state.showPrev
        binding.nextButton.text = getString(state.nextButtonText)
        binding.videoPlayer.isVisible = state.showVideo
        playerState = playerState.checkAndSet(state.videoUrl)
        binding.videoPlayer.play(this, playerState)
    }

    private val gotoNextStepIntent: Flow<GoToNextStepViewIntent>
        get() = binding.nextButton.clicks().map {
            GoToNextStepViewIntent(args.stepInfo.steps)
        }

    private val gotoPreviousStepIntent: Flow<GoToPreviousStepViewIntent>
        get() = binding.previousButton.clicks().map {
            GoToPreviousStepViewIntent(args.stepInfo.steps)
        }

    companion object {
        const val PLAYER_STATE_KEY: String = "p"
    }
}
