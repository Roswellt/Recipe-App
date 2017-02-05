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

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private static Button searchButton;
    private static EditText searchString;

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
        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(
                new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                Intent intent = new Intent("com.example.quang_tri.recipelist.SearchResults");
                intent.putExtra("recipe", searchString.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void setEditText(){
        searchString = (EditText) findViewById(R.id.searchInput);
    }
}
