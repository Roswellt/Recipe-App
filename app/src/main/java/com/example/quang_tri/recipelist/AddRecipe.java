package com.example.quang_tri.recipelist;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URL;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class AddRecipe extends AppCompatActivity {

    private ImageButton saveRecipeButton, returnButton;
    private ImageView imageButton;
    private Realm realm;
    private static int RESULT_LOAD_IMAGE = 1;

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
                        realm.copyToRealmOrUpdate(newRecipe);
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
        imageButton = (ImageView) findViewById(R.id.imageInButton);
        imageButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent i = new Intent(
                                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                        startActivityForResult(i, RESULT_LOAD_IMAGE);
                    }
                }
        );
    }

    //Testing method for image upload function
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imageInButton);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }

    private void addIngredients(Recipe recipe){
        EditText ingredientsIn = (EditText) findViewById(R.id.ingredientIn);
        String[] ingredients = ingredientsIn.getText().toString().split(",");
        //RealmQuery<Ingredient> query = realm.where(Ingredient.class);
        for(String x : ingredients){
            Ingredient newIn = new Ingredient(x.trim().toLowerCase());
            recipe.addIngredient(newIn);
        }
    }
}
