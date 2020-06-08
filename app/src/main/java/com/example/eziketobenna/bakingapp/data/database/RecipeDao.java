package com.example.eziketobenna.bakingapp.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.eziketobenna.bakingapp.data.model.Recipe;

import java.util.List;

/**
 * class with methods for carrying out
 * database operations with {@link AppDatabase}
 */
@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipe")
    LiveData<List<Recipe>> getAllRecipes();

    @Query("SELECT * FROM recipe WHERE id = :id")
    Recipe getSelectedRecipe(int id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void bulkInsert(List<Recipe> recipes);

    @Query("DELETE FROM recipe")
    void deleteAllRecipes();
}
