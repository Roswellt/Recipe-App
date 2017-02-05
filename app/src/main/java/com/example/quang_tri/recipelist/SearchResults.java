package com.example.quang_tri.recipelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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
        RealmQuery<Recipe> query = realm.where(Recipe.class);
        //String recipeName = getIntent().getStringExtra("recipe");
        //query.equalTo("name", recipeName);
        query.equalTo("name", "beans");
        RealmResults<Recipe> result = query.findAll();


        CustomAdapter adapter = new CustomAdapter(this, result);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
