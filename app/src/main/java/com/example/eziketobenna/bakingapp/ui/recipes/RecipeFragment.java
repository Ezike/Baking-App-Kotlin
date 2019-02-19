package com.example.eziketobenna.bakingapp.ui.recipes;


import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.eziketobenna.bakingapp.BakingApplication;
import com.example.eziketobenna.bakingapp.R;
import com.example.eziketobenna.bakingapp.data.model.Recipe;
import com.example.eziketobenna.bakingapp.data.network.NetworkDataSource;
import com.example.eziketobenna.bakingapp.databinding.FragmentRecipeBinding;
import com.example.eziketobenna.bakingapp.ui.details.StepListActivity;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment implements RecipeAdapter.RecipeClickListener {
    public static final String LIST_STATE_KEY = "list_state";
    private static final String LOG_TAG = RecipeFragment.class.getSimpleName();
    private final int PORT_SPAN = 2;
    private RecipeAdapter mAdapter;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private ShimmerFrameLayout mShimmer;
    private Parcelable mListState;
    private boolean isSet;
    private GridLayoutManager mLayoutManager;
    private FrameLayout mFrameLayout;
    private FragmentRecipeBinding binding;

    @Inject
    RecipeViewModelFactory factory;

    @Inject
    NetworkDataSource networkDataSource;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BakingApplication.getComponent(Objects.requireNonNull(getActivity())).inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        binding = FragmentRecipeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initViews();
        checkOrientation();
        setUpViewModel();
        return view;
    }

    // Initialize all views
    private void initViews() {
        mFrameLayout = binding.mainFrame;
        mShimmer = binding.shimmer;
        mRecyclerView = binding.mainRv;
        mLayoutManager = new GridLayoutManager(mContext, PORT_SPAN);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(true);
        mAdapter = new RecipeAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    // Setup ViewModel
    private void setUpViewModel() {
        RecipeViewModel mViewModel = ViewModelProviders.of(this, factory).get(RecipeViewModel.class);
        mViewModel.getAllRecipes().observe((LifecycleOwner) mContext, this::setRecipesToAdapter);
    }

    // Set the recipes from database to the RecyclerView Adapter
    private void setRecipesToAdapter(List<Recipe> recipes) {
        if (recipes != null && recipes.size() != 0) {
            mAdapter.setRecipes(recipes);
            if (mListState != null) {
                mLayoutManager.onRestoreInstanceState(mListState);
            }
            showData();
            mShimmer.stopShimmer();
            mShimmer.setVisibility(View.GONE);
        } else {
            showEmpty();
        }
    }

    // If there's no network, show the snackBar and start shimmer
    private void showEmpty() {
        if (!isConnected()) {
            showSnackBar();
        } else {
            mShimmer.startShimmer();
        }
        isSet = true;
    }

    // If there's data, display the recipes and hide shimmer
    private void showData() {
        if (isSet) {
            mShimmer.setVisibility(View.GONE);
            mShimmer.stopShimmer();
            isSet = false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmer.startShimmer();
    }

    @Override
    public void onPause() {
        super.onPause();
        mShimmer.stopShimmer();
    }

    // Persist recyclerView scroll state
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LIST_STATE_KEY, mLayoutManager.onSaveInstanceState());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null)
            mListState = savedInstanceState.getParcelable(LIST_STATE_KEY);
    }

    // Check if there is internet connection
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnected();
    }

    // Show SnackBar if there's no network or no data available
    private void showSnackBar() {
        Snackbar snackbar = Snackbar
                .make(mFrameLayout, R.string.no_internet, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, view -> {
                    if (isSet) {
                        networkDataSource.fetchRecipes();
                    }
                    showEmpty();
                });
        snackbar.show();
    }

    // Display more items when on Landscape
    private void checkOrientation() {
        if (mRecyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLayoutManager = new GridLayoutManager(mContext, PORT_SPAN);
            mRecyclerView.setLayoutManager(mLayoutManager);
        } else if (mRecyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            int LAND_SPAN = 4;
            mLayoutManager = new GridLayoutManager(mContext, LAND_SPAN);
            mRecyclerView.setLayoutManager(mLayoutManager);
        }
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        Intent in = new Intent(mContext, StepListActivity.class);
        in.putExtra(StepListActivity.INTENT_EXTRA, recipe);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(in);
    }
}
