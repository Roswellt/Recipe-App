package com.example.quang_tri.recipelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class SearchResults extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;

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

            adapter = new CustomAdapter(this, result);
            listView = (ListView) findViewById(R.id.list);

        } else if(getIntent().getStringExtra("searchType").equals("Ingredient Search")){

            RealmQuery<Recipe> query = realm.where(Recipe.class);
            RealmResults<Recipe> results = query.findAll();
            String ingredients = getIntent().getStringExtra("ingredients");
            String[] ingredientList = ingredients.split(",");
            for(String x : ingredientList){
                results = query.equalTo("ingredients.name", x.trim().toLowerCase()).findAll();
            }
            adapter = new CustomAdapter(this, results);
            listView = (ListView) findViewById(R.id.list);
        }
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe recipe = (Recipe) parent.getItemAtPosition(position);
                Toast.makeText(SearchResults.this, recipe.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
