package com.example.quang_tri.recipelist;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Quang-Tri on 03/02/2017.
 */

public class Recipe extends RealmObject{
    private String name;
    private int cookTime;
    private RealmList<Ingredient> ingredients;

    public Recipe(String name, int cookTime){
        this.name = name;
        this.cookTime = cookTime;
        ingredients = new RealmList<Ingredient>();
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
}
