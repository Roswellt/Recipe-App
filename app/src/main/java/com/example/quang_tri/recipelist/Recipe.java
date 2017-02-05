package com.example.quang_tri.recipelist;

import io.realm.RealmObject;

/**
 * Created by Quang-Tri on 03/02/2017.
 */

public class Recipe extends RealmObject{
    public String name;
    public int cookTime;

    public Recipe(String name, int cookTime){
        this.name = name;
        this.cookTime = cookTime;
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
}
