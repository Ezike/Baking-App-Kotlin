package com.example.eziketobenna.bakingapp.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.eziketobenna.bakingapp.R;
import com.example.eziketobenna.bakingapp.data.database.IngredientListConverter;
import com.example.eziketobenna.bakingapp.data.database.StepListConverter;
import com.example.eziketobenna.bakingapp.utils.GlideApp;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "recipe")
public class Recipe {
    @SerializedName("image")
    private String image;

    @SerializedName("servings")
    private int servings;

    @SerializedName("name")
    private String name;

    @TypeConverters(IngredientListConverter.class)
    @SerializedName("ingredients")
    private List<Ingredients> ingredients;

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @TypeConverters(StepListConverter.class)
    @SerializedName("steps")
    private List<Steps> steps;

    public Recipe(String image, int servings, String name, List<Ingredients> ingredients, int id, List<Steps> steps) {
        this.image = image;
        this.servings = servings;
        this.name = name;
        this.ingredients = ingredients;
        this.id = id;
        this.steps = steps;
    }

    @Ignore
    public Recipe() {
    }

    @BindingAdapter({"image"})
    public static void loadImage(ImageView view, String imageUrl) {
        GlideApp.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(view);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public void setSteps(List<Steps> steps) {
        this.steps = steps;
    }
}