package com.example.eziketobenna.bakingapp.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.eziketobenna.bakingapp.data.database.AppDatabase;
import com.example.eziketobenna.bakingapp.data.database.RecipeDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = AppModule.class)
public class RoomModule {
    private AppDatabase appDatabase;

    public RoomModule(Application application) {
        appDatabase = Room.databaseBuilder(application, AppDatabase.class, AppDatabase.DB_NAME).build();
    }

    @Singleton
    @Provides
    AppDatabase provideDatabase() {
        return appDatabase;
    }

    @Singleton
    @Provides
    RecipeDao provideDao() {
        return appDatabase.recipeDao();
    }
}
