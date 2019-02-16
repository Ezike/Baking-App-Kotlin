package com.example.eziketobenna.bakingapp.di;

import com.example.eziketobenna.bakingapp.data.network.RecipeIntentService;
import com.example.eziketobenna.bakingapp.di.modules.ContextModule;
import com.example.eziketobenna.bakingapp.di.modules.ExecutorsModule;
import com.example.eziketobenna.bakingapp.di.modules.NetworkModule;
import com.example.eziketobenna.bakingapp.di.modules.RoomModule;
import com.example.eziketobenna.bakingapp.ui.recipes.RecipeFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RoomModule.class, NetworkModule.class, ExecutorsModule.class, ContextModule.class})
public interface BakingComponent {

    void inject(RecipeFragment fragment);

    void inject(RecipeIntentService service);

}
