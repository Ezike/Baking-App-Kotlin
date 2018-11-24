package com.example.eziketobenna.bakingapp.ui.details;

import android.annotation.TargetApi;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.eziketobenna.bakingapp.R;
import com.example.eziketobenna.bakingapp.data.model.Step;
import com.example.eziketobenna.bakingapp.databinding.ActivityStepDetailBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a single Step detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link StepListActivity}.
 */
public class StepDetailActivity extends AppCompatActivity implements StepDetailFragment.OnStepClickListener {
    public static final String EXTRA = "step";
    public static final String EXTRA_LIST = "step_list";
    public static final String STEP_INDEX = "index";
    public static final String STEP_LIST = "current list";
    static final String LOG_TAG = StepDetailActivity.class.getSimpleName();
    int stepIndex;
    private List<Step> mStepList;
    Step step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStepDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_step_detail);
        Toolbar toolbar = binding.detailListToolbar;
        setSupportActionBar(toolbar);
        setTitle(R.string.step_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            step = getIntent().getParcelableExtra((EXTRA));
            mStepList = getIntent().getParcelableArrayListExtra(StepDetailActivity.EXTRA_LIST);
            createFragment();
        } else {
            mStepList = savedInstanceState.getParcelableArrayList(STEP_LIST);
            stepIndex = savedInstanceState.getInt(STEP_INDEX);
        }
    }

    private void createFragment() {
        StepDetailFragment fragment = StepDetailFragment.newInstance(step);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.step_detail_container, fragment)
                .commit();
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STEP_INDEX, stepIndex);
        outState.putParcelableArrayList(STEP_LIST, (ArrayList<? extends Parcelable>) mStepList);
    }

    @Override
    public void onPrevStepClick(Step step) {
        stepIndex = step.getId();
        if (stepIndex > 0) {
            gotoStep(mStepList.get(stepIndex - 1));
        } else {
            finish();
        }
    }

    @Override
    public void onNextStepClick(Step step) {
        stepIndex = step.getId();
        if (stepIndex < mStepList.size() - 1) {
            gotoStep(mStepList.get(stepIndex + 1));
        } else {
            finish();
        }


    }

    private void gotoStep(Step step) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        StepDetailFragment newFragment = StepDetailFragment.newInstance(step);
        transaction.replace(R.id.step_detail_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
