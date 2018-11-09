package com.example.eziketobenna.bakingapp.utils;

import android.content.Context;

import com.example.eziketobenna.bakingapp.AppExecutors;
import com.example.eziketobenna.bakingapp.data.network.NetworkDataSource;

/**
 * Provides static methods to inject the various classes needed for Sunshine
 */
public class InjectorUtils {
    public static NetworkDataSource provideNetworkDataSource(Context context) {
        AppExecutors executors = AppExecutors.getInstance();
        return NetworkDataSource.getInstance(context.getApplicationContext(), executors);

    }
}
