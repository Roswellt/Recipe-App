package com.example.quang_tri.recipelist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Button searchRecipeButton, addRecipeButton, searchIngredientsButton, debugButton;
    private EditText searchString, ingredientSearchString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding recipes to the database
        //Recipe beans = new Recipe("beans", 10);
        //realm.beginTransaction();
        //realm.copyToRealm(beans);
        //realm.commitTransaction();

        setEditText();

        setButtonListener();
    }

    public void setButtonListener(){
        searchRecipeButton = (Button) findViewById(R.id.searchRecipeButton);
        searchRecipeButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.quang_tri.recipelist.SearchResults");
                        intent.putExtra("searchType", "Recipe Search");
                        intent.putExtra("recipe", searchString.getText().toString().trim());
                        startActivity(intent);
            }
        });
        addRecipeButton = (Button) findViewById(R.id.addRecipeButton);
        addRecipeButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.quang_tri.recipelist.AddRecipe");
                        startActivity(intent);
                    }
                }
        );
        searchIngredientsButton = (Button) findViewById(R.id.ingredientsSearchButton);
        searchIngredientsButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent("com.example.quang_tri.recipelist.SearchResults");
                        intent.putExtra("searchType", "Ingredient Search");
                        intent.putExtra("ingredients", ingredientSearchString.getText().toString());
                        startActivity(intent);
                    }
                }
        );
        //Clears realm database
        debugButton = (Button) findViewById(R.id.debugButton);
        debugButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Realm realm = Realm.getDefaultInstance();
                        Toast.makeText(MainActivity.this, "DB cleared", Toast.LENGTH_SHORT).show();
                        realm.beginTransaction();
                        realm.deleteAll();
                        realm.commitTransaction();
                    }
                }
        );
    }

    public void setEditText(){
        searchString = (EditText) findViewById(R.id.searchInput);
        ingredientSearchString = (EditText) findViewById(R.id.ingredientIn);
    }

}
