package com.example.quang_tri.recipelist;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import io.realm.Realm;

public class AddRecipe extends AppCompatActivity {

    private ImageButton saveRecipeButton;
    private Realm realm;
    private ImageButton returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        realm = Realm.getDefaultInstance();

        setOnClick();
    }

    public void setOnClick(){
        saveRecipeButton = (ImageButton) findViewById(R.id.saveRecipeButton);
        saveRecipeButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        //Adding recipes to the database
                        EditText recipeName = (EditText) findViewById(R.id.recipeNameIn);
                        EditText cookTime = (EditText) findViewById(R.id.cookTimeIn);
                        Recipe newRecipe = new Recipe(recipeName.getText().toString(), Integer.parseInt(cookTime.getText().toString()));
                        addIngredients(newRecipe);
                        realm.beginTransaction();
                        realm.copyToRealm(newRecipe);
                        realm.commitTransaction();

                        //Visual confirmation and return to main screen
                        Toast.makeText(AddRecipe.this, recipeName.getText().toString()+ " added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent("com.example.quang_tri.recipelist.MainActivity");
                        startActivity(intent);
                    }
                }
        );
        returnButton = (ImageButton) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.quang_tri.recipelist.MainActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    private void addIngredients(Recipe recipe){
        EditText ingredientsIn = (EditText) findViewById(R.id.ingredientIn);
        String[] ingredients = ingredientsIn.getText().toString().split(",");
        for(String x : ingredients){
            Ingredient newIn = new Ingredient(x.trim().toLowerCase());
            recipe.addIngredient(newIn);
        }
    }
}
