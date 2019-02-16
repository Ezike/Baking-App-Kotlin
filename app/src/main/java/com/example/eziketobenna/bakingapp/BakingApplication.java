package com.example.eziketobenna.bakingapp;

import android.app.Application;
import android.content.Context;

import com.example.eziketobenna.bakingapp.di.BakingComponent;
import com.example.eziketobenna.bakingapp.di.DaggerBakingComponent;
import com.example.eziketobenna.bakingapp.di.modules.ContextModule;
import com.example.eziketobenna.bakingapp.di.modules.RoomModule;

public class BakingApplication extends Application {

    private BakingComponent bakingComponent;

    public static BakingComponent getComponent(Context context) {
        return ((BakingApplication) context.getApplicationContext()).bakingComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        bakingComponent = DaggerBakingComponent.builder()
                .contextModule(new ContextModule(this))
                .roomModule(new RoomModule(this))
                .build();
    }
}
