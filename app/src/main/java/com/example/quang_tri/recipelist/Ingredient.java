package com.example.quang_tri.recipelist;

import io.realm.RealmModel;

/**
 * Created by Quang-Tri on 05/02/2017.
 */

public class Ingredient implements RealmModel {
    private String name;

    public Ingredient(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}