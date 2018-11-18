package com.example.eziketobenna.bakingapp.ui.details;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.eziketobenna.bakingapp.R;
import com.example.eziketobenna.bakingapp.data.model.Ingredient;
import com.example.eziketobenna.bakingapp.data.model.Recipe;
import com.example.eziketobenna.bakingapp.data.model.Step;
import com.example.eziketobenna.bakingapp.databinding.ActivityStepListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Step. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link StepDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class StepListActivity extends AppCompatActivity implements DetailAdapter.StepClickListener {

    public static final String INTENT_EXTRA = "recipe";
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private static final String LOG_TAG = StepListActivity.class.getSimpleName();
    private boolean mTwoPane;
    ActivityStepListBinding binding;
    Toolbar toolbar;
    private RecyclerView mRecyclerView;
    private Recipe mRecipe;
    private List<Ingredient> mIngredientList;
    private List<Step> mStepList;
    private String mRecipeName;
    private ArrayList<Object> mBakingObjects;
    DetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_step_list);

        toolbar = binding.stepListToolbar;

        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mBakingObjects = new ArrayList<>();

        //Get intent extras
        Intent in = getIntent();
        if (in == null) {
            closeOnError();
        }
        assert in != null;
        if (in.hasExtra(INTENT_EXTRA)) {
            mRecipe = getIntent().getParcelableExtra(INTENT_EXTRA);
            mIngredientList = mRecipe.getIngredients();
            mStepList = mRecipe.getSteps();
            mRecipeName = mRecipe.getName();
            mBakingObjects.addAll(mIngredientList);
            mBakingObjects.addAll(mStepList);

            // Set toolbar title
            setTitle(mRecipeName);
//
//            // Log to check if the right objects are gotten
//            Log.d(LOG_TAG, "onCreate: " + mRecipe.toString());
        }

        if (findViewById(R.id.step_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        mRecyclerView = findViewById(R.id.step_list);
        assert mRecyclerView != null;
        mAdapter = new DetailAdapter(this, mBakingObjects, mTwoPane, this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.no_recipe_data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStepClick(Step step) {
        if (step != null) {
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putParcelable(StepDetailFragment.EXTRA, step);
                StepDetailFragment fragment = new StepDetailFragment();
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.step_detail_container, fragment)
                        .commit();
            } else {
                Intent intent = new Intent(this, StepDetailActivity.class);
                intent.putExtra(StepDetailFragment.EXTRA, step);
                startActivity(intent);
            }

        }

    }
}
