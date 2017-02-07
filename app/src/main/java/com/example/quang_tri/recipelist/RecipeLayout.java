package com.example.quang_tri.recipelist;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RecipeLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_layout);

        String primaryKey = getIntent().getStringExtra("recipe");
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Recipe> query = realm.where(Recipe.class);
        query.equalTo("instructions", primaryKey);
        RealmResults<Recipe> results = query.findAll();
        Recipe recipe = results.first();
        populateViews(recipe);
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

}
