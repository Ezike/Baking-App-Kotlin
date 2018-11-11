package com.example.eziketobenna.bakingapp.data.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

class IngredientListConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Ingredients> toList(String string) {
        if (string == null) {
            return Collections.emptyList();
        }
        Type ingredientListType = new TypeToken<List<Ingredients>>() {
        }.getType();
        return gson.fromJson(string, ingredientListType);
    }

    @TypeConverter
    public static String toString(List<Ingredients> ingredientList) {
        return ingredientList == null ? null : gson.toJson(ingredientList);
    }
}
