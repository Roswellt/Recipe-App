package com.example.quang_tri.recipelist;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;
import io.realm.RealmObject;

/**
 * Created by Quang-Tri on 03/02/2017.
 */

public class CustomAdapter extends RealmBaseAdapter<Recipe> implements ListAdapter {

    public Activity context;
    public OrderedRealmCollection<Recipe> recipes;


    public CustomAdapter(Activity context, OrderedRealmCollection<Recipe> recipes) {
        super(context, recipes);
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        TextView text = (TextView) rowView.findViewById(R.id.txt);
        ImageView recipePic = (ImageView) rowView.findViewById(R.id.recipePic);

        Recipe recipe = recipes.get(position);
        text.setText(recipe.getName());
        recipePic.setImageBitmap(BitmapFactory.decodeFile(recipe.getPicture()));

        return rowView;
    }
}
