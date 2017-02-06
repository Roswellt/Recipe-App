package com.example.quang_tri.recipelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class SearchResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Realm realm = Realm.getDefaultInstance();


        //Testing query
        if(getIntent().getStringExtra("searchType").equals("Recipe Search")) {
            RealmQuery<Recipe> query = realm.where(Recipe.class);
            String recipeName = getIntent().getStringExtra("recipe");
            Toast.makeText(SearchResults.this, recipeName, Toast.LENGTH_SHORT).show();
            query.equalTo("name", recipeName);
            RealmResults<Recipe> result = query.findAll();

            CustomAdapter adapter = new CustomAdapter(this, result);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
        } else if(getIntent().getStringExtra("searchType").equals("Ingredient Search")){
            RealmQuery<Recipe> query = realm.where(Recipe.class);
            RealmResults<Recipe> results = query.findAll();
            String ingredients = getIntent().getStringExtra("ingredients");
            String[] ingredientList = ingredients.split(",");
            for(String x : ingredientList){
                results = query.equalTo("ingredients.name", x.trim().toLowerCase()).findAll();
            }
            CustomAdapter adapter = new CustomAdapter(this, results);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
        }
    }
}
