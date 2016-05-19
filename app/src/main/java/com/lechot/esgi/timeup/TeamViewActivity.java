package com.lechot.esgi.timeup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Bastien on 18/05/2016.
 */
public class TeamViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamview);
        ArrayList<String> TeamA = new ArrayList<>();
        ArrayList<String> TeamB = new ArrayList<>();
        for (int i =0;i<GameData.TeamA.size();i++){
            TeamA.add(GameData.TeamA.get(i).getName());
            TeamB.add(GameData.TeamB.get(i).getName());
        }
        ArrayAdapter<String> itemsAdapterTeamA =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TeamA);
        ArrayAdapter<String> itemsAdapterTeamB =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TeamB);

        ListView listViewTeamA = (ListView) findViewById(R.id.listViewTeamA);
        listViewTeamA.setAdapter(itemsAdapterTeamA);
        ListView listViewTeamB = (ListView) findViewById(R.id.listViewTeamB);
        listViewTeamB.setAdapter(itemsAdapterTeamB);
    }
    public void onPlayClicked(View v) {
        Intent nextPageIntent = new Intent(this, CategoryActivity.class);
        startActivity(nextPageIntent);
    }

}
