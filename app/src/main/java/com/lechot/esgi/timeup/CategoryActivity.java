package com.lechot.esgi.timeup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Bastien on 19/05/2016.
 */
public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }

    public void onClickAnimals(View v) {
        Intent nextPageIntent = new Intent(this, MainScreenGameActivity.class);
        GameData.SelectedCategories = GameData.CATEGORIES.Animals;
        startActivity(nextPageIntent);
    }
    public void onClickGames(View v) {
        Intent nextPageIntent = new Intent(this, MainScreenGameActivity.class);
        GameData.SelectedCategories = GameData.CATEGORIES.Games;
        startActivity(nextPageIntent);
    }
    public void onClickHistory(View v) {
        Intent nextPageIntent = new Intent(this, MainScreenGameActivity.class);
        GameData.SelectedCategories = GameData.CATEGORIES.History;
        startActivity(nextPageIntent);
    }
    public void onClickCelebrety(View v) {
        Intent nextPageIntent = new Intent(this, MainScreenGameActivity.class);
        GameData.SelectedCategories = GameData.CATEGORIES.Celebrety;
        startActivity(nextPageIntent);
    }
}
