package com.example.eziketobenna.bakingapp.data.network;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.eziketobenna.bakingapp.utils.InjectorUtils;

public class RecipeIntentService extends IntentService {
    private static final String LOG_TAG = RecipeIntentService.class.getSimpleName();

    public RecipeIntentService() {
        super("RecipeIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(LOG_TAG, "Intent service started");
        NetworkDataSource networkDataSource = InjectorUtils.provideNetworkDataSource(this.getApplicationContext());
        networkDataSource.fetchRecipes();
    }
}
