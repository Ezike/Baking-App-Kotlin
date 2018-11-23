package com.example.eziketobenna.bakingapp.ui.details;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eziketobenna.bakingapp.R;
import com.example.eziketobenna.bakingapp.data.model.Step;
import com.example.eziketobenna.bakingapp.databinding.StepDetailBinding;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * A fragment representing a single Step detail screen.
 * This fragment is either contained in a {@link StepListActivity}
 * in two-pane mode (on tablets) or a {@link StepDetailActivity}
 * on handsets.
 */
public class StepDetailFragment extends Fragment implements Player.EventListener {
    public static final String LOG_TAG = StepDetailFragment.class.getSimpleName();
    public static final String EXTRA = "step";
    private TextView mDescriptionTv;
    private static final String ARG_POSITION = "position";
    private ImageView mIageView;
    int orientation;
    private SimpleExoPlayer mExoPlayer;
    private PlayerView mPlayerView;
    private long mPlaybackPosition;
    Context mContext;
    String videoUrl;
    Step step;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StepDetailFragment() {
    }

    public static StepDetailFragment newInstance(int position) {
        StepDetailFragment fragment = new StepDetailFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        int position = getArguments().getInt(ARG_POSITION);
        if (getArguments() != null && getArguments().containsKey(EXTRA)) {
            step = getArguments().getParcelable(EXTRA);
        }

        if (savedInstanceState != null) {
            mPlaybackPosition = savedInstanceState.getLong(ARG_POSITION);
        } else {
            mPlaybackPosition = 0;
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StepDetailBinding binding = StepDetailBinding.inflate(inflater, container, false);
        binding.setSteps(step);
        mContext = getActivity();
        mPlayerView = binding.playerView;
        mIageView = binding.errorImage;
        mDescriptionTv = binding.stepDetail;
        orientation = getResources().getConfiguration().orientation;
        return binding.getRoot();
    }

    // initialise exo player
    public void initPlayer() {
        assert getArguments() != null;
        step = getArguments().getParcelable(EXTRA);
        assert step != null;
        videoUrl = step.getVideoURL();
        if (mExoPlayer == null && !(videoUrl.isEmpty())) {
            mPlayerView.setVisibility(View.VISIBLE);
            TrackSelector trackSelector = new DefaultTrackSelector();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector);
            mPlayerView.setPlayer(mExoPlayer);
            mExoPlayer.addListener(this);
            String userAgent = Util.getUserAgent(mContext, getString(R.string.app_name));
            DataSource.Factory dataSourceFactory =
                    new DefaultDataSourceFactory(mContext, userAgent);
            MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(videoUrl));
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.seekTo(mPlaybackPosition);
            mExoPlayer.setPlayWhenReady(true);

        } else {
            // hide the video view
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mPlayerView.setVisibility(View.GONE);
                mIageView.setVisibility(View.VISIBLE);
                mDescriptionTv.setVisibility(View.VISIBLE);
                // In landscape
            } else {
                mPlayerView.setVisibility(View.GONE);
                mIageView.setVisibility(View.VISIBLE);
                // In portrait
            }
        }
    }

    void releasePlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initPlayer();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mExoPlayer != null)
            mPlaybackPosition = mExoPlayer.getContentPosition();
        releasePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (playWhenReady && playbackState == Player.STATE_READY) {
            Log.d(LOG_TAG, "Player is playing");
        } else if (playbackState == Player.STATE_READY) {
            Log.d(LOG_TAG, "Player is paused");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putLong(ARG_POSITION, mPlaybackPosition);
        super.onSaveInstanceState(outState);
    }
}
