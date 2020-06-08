package com.example.eziketobenna.bakingapp.ui.details;

import android.annotation.TargetApi;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import com.example.eziketobenna.bakingapp.R;
import com.example.eziketobenna.bakingapp.data.model.Step;
import com.example.eziketobenna.bakingapp.databinding.ActivityStepDetailBinding;
import com.example.eziketobenna.bakingapp.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a single Step detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link StepListActivity}.
 */
public class StepDetailActivity extends BaseActivity implements StepDetailFragment.OnStepClickListener {
    public static final String EXTRA = "com.example.eziketobenna.bakingapp.ui.details.Step";
    public static final String EXTRA_LIST = "com.example.eziketobenna.bakingapp.ui.details.steplist";
    public static final String EXTRA_NAME = "com.example.eziketobenna.bakingapp.ui.details.recipeName";
    public static final String STEP_INDEX = "index";
    public static final String STEP_LIST = "current list";
    public static final String LIST_END = "end of list";
    private static final String RECIPE_DETAIL_FRAG = "com.example.eziketobenna.bakingapp.ui.details.RECIPE.DETAIL.FRAG";
    private static final String CURRENT_STEP = "com.example.eziketobenna.bakingapp.ui.details.current.step";
    private List<Step> mStepList;
    private int mCurrentPosition;
    private Step mStep;
    private int mEndOfList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStepDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_step_detail);
        Toolbar toolbar = binding.detailListToolbar;
        setSupportActionBar(toolbar);
        String mRecipeName = getIntent().getStringExtra(EXTRA_NAME);
        setTitle(mRecipeName);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {
            mStep = getIntent().getParcelableExtra((EXTRA));
            mStepList = getIntent().getParcelableArrayListExtra(StepDetailActivity.EXTRA_LIST);
            mEndOfList = mStepList.size() - 1;
        } else {
            mStep = savedInstanceState.getParcelable(CURRENT_STEP);
            mStepList = savedInstanceState.getParcelableArrayList(STEP_LIST);
            mCurrentPosition = savedInstanceState.getInt(STEP_INDEX);
            mEndOfList = savedInstanceState.getInt(LIST_END);
        }
        createFragment();
    }

    private void createFragment() {
        FragmentManager manager = getSupportFragmentManager();

        StepDetailFragment fragment = (StepDetailFragment) manager.findFragmentByTag(RECIPE_DETAIL_FRAG);

        if (fragment == null) {
            fragment = StepDetailFragment.newInstance(mStep, mEndOfList);
        }

        addFragmentToActivity(
                manager,
                fragment,
                R.id.step_detail_container,
                RECIPE_DETAIL_FRAG
        );
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown.
            navigateUpTo(new Intent(this, StepListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STEP_INDEX, mCurrentPosition);
        outState.putInt(LIST_END, mEndOfList);
        outState.putParcelable(CURRENT_STEP, mStep);
        outState.putParcelableArrayList(STEP_LIST, (ArrayList<? extends Parcelable>) mStepList);
    }

    @Override
    public void onPrevStepClick(Step step) {
        mCurrentPosition = step.getId();
        if (mCurrentPosition > 0) {
            gotoStep(mStepList.get(mCurrentPosition - 1));
        } else {
            finish();
        }
    }

    @Override
    public void onNextStepClick(Step step) {
        mCurrentPosition = step.getId();
        if (mCurrentPosition < mStepList.size() - 1) {
            gotoStep(mStepList.get(mCurrentPosition + 1));
        } else {
            finish();
        }
    }

    private void gotoStep(Step step) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        StepDetailFragment newFragment = StepDetailFragment.newInstance(step, mEndOfList);
        transaction.replace(R.id.step_detail_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
