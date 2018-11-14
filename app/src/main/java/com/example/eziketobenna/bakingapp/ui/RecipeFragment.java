package com.example.eziketobenna.bakingapp.ui;


import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eziketobenna.bakingapp.data.model.Recipe;
import com.example.eziketobenna.bakingapp.databinding.FragmentRecipeBinding;
import com.example.eziketobenna.bakingapp.utils.InjectorUtils;
import com.facebook.shimmer.ShimmerFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment implements RecipeAdapter.RecipeClickListener {
    public final static String LIST_STATE_KEY = "list_state";
    private static final String LOG_TAG = RecipeFragment.class.getSimpleName();
    RecyclerView mRecyclerView;
    ShimmerFrameLayout mShimmer;
    private RecipeAdapter mAdapter;
    private Context mContext;
    private Parcelable listState;
    private GridLayoutManager gridLayoutManager;
    FragmentRecipeBinding binding;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        initViews();
        checkOrientation();
        setUpViewModel();
        return view;
    }

    // Setup ViewModel
    private void setUpViewModel() {
        Log.d(LOG_TAG, "Viewmodel setup");
        RecipeViewModelFactory factory = InjectorUtils.provideRecipeViewModelFactory(mContext);
        RecipeViewModel viewModel = ViewModelProviders.of(this, factory).get(RecipeViewModel.class);
        viewModel.getAllRecipes().observe((LifecycleOwner) mContext, recipes -> {
            Log.d(LOG_TAG, "Displaying recipes in fragment");
            mAdapter.setRecipes(recipes);
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LIST_STATE_KEY, gridLayoutManager.onSaveInstanceState());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null)
            listState = savedInstanceState.getParcelable(LIST_STATE_KEY);
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

    // Initial views
    private void initViews() {
        mShimmer = binding.shimmer;
        mRecyclerView = binding.mainRv;
        gridLayoutManager = new GridLayoutManager(mContext, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(true);
        mAdapter = new RecipeAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
    }

    // Display more items when on Landscape
    private void checkOrientation() {
        if (mRecyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridLayoutManager = new GridLayoutManager(mContext, 2);
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else if (mRecyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridLayoutManager = new GridLayoutManager(mContext, 4);
            mRecyclerView.setLayoutManager(gridLayoutManager);
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

    @Override
    public void onRecipeClick(Recipe recipe) {
        Log.d(LOG_TAG, "Recipe clicked");
    }
}
