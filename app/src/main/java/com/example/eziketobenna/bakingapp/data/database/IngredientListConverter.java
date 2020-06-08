package com.example.eziketobenna.bakingapp.data.database;

import androidx.room.TypeConverter;

import com.example.eziketobenna.bakingapp.data.model.Ingredient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * converter class for {@link Ingredient}
 * used in {@link AppDatabase}
 */
public class IngredientListConverter {

    private static Gson gson = new Gson();

    // returns Ingredient List
    @TypeConverter
    public static List<Ingredient> toList(String string) {
        if (string == null) {
            return Collections.emptyList();
        }
        Type ingredientListType = new TypeToken<List<Ingredient>>() {
        }.getType();
        return gson.fromJson(string, ingredientListType);
    }

    // returns the String
    @TypeConverter
    public static String toString(List<Ingredient> ingredientList) {
        return ingredientList == null ? null : gson.toJson(ingredientList);
    }
}
