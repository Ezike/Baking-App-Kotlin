package com.example.eziketobenna.bakingapp.data.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

class StepListConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Steps> StringToStepsList(String string) {
        if (string == null) {
            return Collections.emptyList();
        }
        Type stepListType = new TypeToken<List<Steps>>() {
        }.getType();
        return gson.fromJson(string, stepListType);
    }

    @TypeConverter
    public static String StepListToString(List<Steps> stepsList) {
        return stepsList == null ? null : gson.toJson(stepsList);
    }
}
