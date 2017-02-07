package com.example.quang_tri.recipelist;

import android.graphics.Bitmap;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Quang-Tri on 03/02/2017.
 */

public class Recipe extends RealmObject{
    private String name;
    private String picturePath;
    private RealmList<Ingredient> ingredients;
    private int cookTime;
    @PrimaryKey
    private String instructions;

    public Recipe(String name, int cookTime, String instructions){
        this.name = name;
        this.cookTime = cookTime;
        ingredients = new RealmList<Ingredient>();
        this.instructions = instructions;
    }

    public Recipe(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public RealmList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public String getPicture() {
        return picturePath;
    }

    public void setPicture(String picture) {
        this.picturePath = picture;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
