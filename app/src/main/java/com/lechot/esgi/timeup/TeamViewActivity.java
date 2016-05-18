package com.lechot.esgi.timeup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

/**
 * Created by Bastien on 18/05/2016.
 */
public class TeamViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Intent intent = getIntent();
        String team1 = intent.getStringExtra("team1");
        String team2 = intent.getStringExtra("team2");

    }

}
