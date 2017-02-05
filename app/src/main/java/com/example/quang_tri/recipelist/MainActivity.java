package com.example.quang_tri.recipelist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Realm realm = Realm.getDefaultInstance();
        //Adding recipes to the database

        Recipe beans = new Recipe("beans", 10);
        realm.beginTransaction();
        realm.copyToRealm(beans);
        realm.commitTransaction();

        //Testing query
        RealmQuery<Recipe> query = realm.where(Recipe.class);
        query.equalTo("name", "beans");
        RealmResults<Recipe> result = query.findAll();


        setContentView(R.layout.activity_main);
        CustomAdapter adapter = new CustomAdapter(this, result);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
