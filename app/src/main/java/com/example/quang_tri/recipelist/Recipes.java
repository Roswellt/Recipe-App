package com.example.quang_tri.recipelist;

import io.realm.RealmObject;

/**
 * Created by Quang-Tri on 03/02/2017.
 */

public class Recipes extends RealmObject{
    public String name;
    public String cookTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }
}
