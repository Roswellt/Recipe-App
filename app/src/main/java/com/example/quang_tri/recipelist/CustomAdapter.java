package com.example.quang_tri.recipelist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Quang-Tri on 03/02/2017.
 */

public class CustomAdapter extends ArrayAdapter<Recipes> {

    private final Activity context;
    private ArrayList<Recipes> recipeList;

    public CustomAdapter(Activity context, ArrayList<Recipes> recipes){
        super(context, 0, recipes);
        this.context = context;
        this.recipeList = recipes;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        TextView text = (TextView) rowView.findViewById(R.id.txt);

        text.setText(recipeList.get(position).getName());

        return rowView;
    }
}
