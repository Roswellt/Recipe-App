package com.example.quang_tri.recipelist;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RecipeLayout extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner spinner;
    private Realm realm;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_layout);

        String primaryKey = getIntent().getStringExtra("recipe");
        realm = Realm.getDefaultInstance();
        RealmQuery<Recipe> query = realm.where(Recipe.class);
        query.equalTo("instructions", primaryKey);
        RealmResults<Recipe> results = query.findAll();
        recipe = results.first();
        populateViews(recipe);

        //Creating the spinner and setting its event listener
        addToSpinner();
        spinner.setOnItemSelectedListener(this);
    }

    private void populateViews(Recipe recipe){
        TextView name = (TextView) findViewById(R.id.recipeName);
        TextView cookTime = (TextView) findViewById(R.id.cookTime);
        TextView ingredients = (TextView) findViewById(R.id.ingredientsText);
        TextView instructions = (TextView) findViewById(R.id.instructionsText);

        name.setText(recipe.getName());
        cookTime.setText(Integer.toString(recipe.getCookTime()));

        //Set imageview
        ImageView recipePic = (ImageView) findViewById(R.id.recipeImage);
        recipePic.setImageBitmap(BitmapFactory.decodeFile(recipe.getPicture()));

        //Turn ingredients into readable string
        RealmList<Ingredient> ingredientsList = recipe.getIngredients();
        StringBuilder ingredientsString = new StringBuilder();
        Iterator<Ingredient> iter = ingredientsList.iterator();
        while(iter.hasNext()){
            ingredientsString.append(iter.next().getName());
            ingredientsString.append("\n");
        }
        ingredients.setText(ingredientsString);

        instructions.setText(recipe.getInstructions());
    }

    public void addToSpinner(){
        spinner = (Spinner) findViewById(R.id.settings);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("Delete");
        list.add("Edit");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    //Main logic for the spinner onClick
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String option = spinner.getSelectedItem().toString();
        switch(option){
            case "":
                break;
            case "Delete":
                Intent intent = new Intent("com.example.quang_tri.recipelist.MainActivity");
                Toast.makeText(RecipeLayout.this, "Recipe deleted", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                RealmQuery<Recipe> query = realm.where(Recipe.class);
                query.equalTo("instructions", recipe.getInstructions());
                RealmResults<Recipe> results = query.findAll();
                realm.beginTransaction();
                results.deleteAllFromRealm();
                realm.commitTransaction();
                break;
            case "Edit":
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

    }
}
