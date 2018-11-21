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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.eziketobenna.bakingapp.R;
import com.example.eziketobenna.bakingapp.data.model.Recipe;
import com.example.eziketobenna.bakingapp.databinding.FragmentRecipeBinding;
import com.example.eziketobenna.bakingapp.ui.details.StepListActivity;
import com.example.eziketobenna.bakingapp.utils.InjectorUtils;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment implements RecipeAdapter.RecipeClickListener {
    public static final String LIST_STATE_KEY = "list_state";
    private static final String LOG_TAG = RecipeFragment.class.getSimpleName();
    private final int PORT_SPAN = 2;
    private final int LAND_SPAN = 4;
    private RecipeAdapter mAdapter;
    private Context mContext;
    private RecyclerView mRecyclerView;
    RecipeViewModel mViewModel;
    private ShimmerFrameLayout mShimmer;
    private Parcelable mListState;
    FrameLayout mFrameLayout;
    private GridLayoutManager mLayoutManager;
    FragmentRecipeBinding binding;

    public RecipeFragment() {
        // Required empty public constructor
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
        checkIfConnected();
        return view;
    }

    // Initial views
    private void initViews() {
        mFrameLayout = binding.mainFrame;
        mShimmer = binding.shimmer;
        mRecyclerView = binding.mainRv;
        mLayoutManager = new GridLayoutManager(mContext, PORT_SPAN);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(true);
        mAdapter = new RecipeAdapter(mContext, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    // Setup ViewModel
    private void setUpViewModel() {
        Log.d(LOG_TAG, "ViewModel setup");
        RecipeViewModelFactory factory = InjectorUtils.provideRecipeViewModelFactory(mContext);
        mViewModel = ViewModelProviders.of(this, factory).get(RecipeViewModel.class);
        getRecipes(mViewModel);
    }

    // Get recipes from ViewModel
    private void getRecipes(RecipeViewModel viewModel) {
        viewModel.getAllRecipes().observe((LifecycleOwner) mContext, this::setRecipesToAdapter);
    }

    // Set the recipes from database to the RecyclerView Adapter
    private void setRecipesToAdapter(List<Recipe> recipes) {
        mShimmer.startShimmer();
        if (recipes != null && recipes.size() != 0) {
            mAdapter.setRecipes(recipes);
            if (mListState != null) {
                mLayoutManager.onRestoreInstanceState(mListState);
            }
            Log.d(LOG_TAG, "Displaying recipes");
            mShimmer.stopShimmer();
            mShimmer.setVisibility(View.GONE);

        }
    }

    private void checkIfConnected() {
        if (!isConnected()) {
            showSnackBar();
            mShimmer.startShimmer();
        } else {
            mShimmer.setVisibility(View.GONE);
            mShimmer.stopShimmer();
        }
    }

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

    /*Show SnackBar if there's an error*/
    private void showSnackBar() {
        Snackbar snackbar = Snackbar
                .make(mFrameLayout, R.string.no_internet, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, view -> {
                    Log.d(LOG_TAG, "Retrying network fetch");
                });
        snackbar.show();
    }

    // Display more items when on Landscape
    private void checkOrientation() {
        if (mRecyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLayoutManager = new GridLayoutManager(mContext, PORT_SPAN);
            mRecyclerView.setLayoutManager(mLayoutManager);
        } else if (mRecyclerView.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
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
        Log.d(LOG_TAG, "Recipe clicked");
    }
}
