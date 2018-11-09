package com.example.eziketobenna.bakingapp.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;


/**
 * {@link AppDatabase} database for the application including a table for {@link Recipe}
 * with the DAO {@link RecipeDao}.
 */

// List of the entry classes and associated TypeConverters
@Database(entities = Recipe.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final String DB_NAME = "recipe_db";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(final Context context) {
        Log.d(LOG_TAG, "Getting the database");
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME).build();
                    Log.d(LOG_TAG, "Made new database");
                }

            }
        }
        return sInstance;
    }

    // The associated DAOs for the database
    public abstract RecipeDao recipeDao();

}
