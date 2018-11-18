package com.example.eziketobenna.bakingapp.ui.details;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.eziketobenna.bakingapp.R;
import com.example.eziketobenna.bakingapp.data.model.Step;
import com.example.eziketobenna.bakingapp.databinding.ActivityStepDetailBinding;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

/**
 * An activity representing a single Step detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link StepListActivity}.
 */
public class StepDetailActivity extends AppCompatActivity implements StepperLayout.StepperListener {
    //    private StepperAdapter mStepperAdapter;
    Step step;
    private StepperLayout mStepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStepDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_step_detail);
        Toolbar toolbar = binding.detailListToolbar;
        setSupportActionBar(toolbar);
        mStepperLayout = binding.stepperLayout;
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.

        step = getIntent().getParcelableExtra((StepDetailFragment.EXTRA));
        String shortDesc = step.getShortDescription();
        setTitle(step.getShortDescription());
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(StepDetailFragment.EXTRA, step);
            StepDetailFragment fragment = new StepDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_detail_container, fragment)
                    .commit();
        }
    }

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
    public void onCompleted(View completeButton) {

    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {

    }

    @Override
    public void onReturn() {

    }
}
