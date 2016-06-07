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
        }
        for (int i =0;i<GameData.TeamB.size();i++) {
            TeamB.add(GameData.TeamB.get(i).getName());
        }

        // Très bonne utilisation des méthodes par défaut proposées par l'ArrayAdapter.
        ArrayAdapter<String> itemsAdapterTeamA =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TeamA);
        ArrayAdapter<String> itemsAdapterTeamB =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TeamB);

        ListView listViewTeamA = (ListView) findViewById(R.id.listViewTeamA);
        // Attention tout de même, findViewById() peut renvoyer null si la vue n'est pas trouvé dans le layout. (Ou qu'elle est appelée trop tôt.)
        // Elle peut renvoyer null. Du coup null.setAdapter(...) --> NullPointerException
        listViewTeamA.setAdapter(itemsAdapterTeamA);
        ListView listViewTeamB = (ListView) findViewById(R.id.listViewTeamB);
        listViewTeamB.setAdapter(itemsAdapterTeamB);
    }
    public void onPlayClicked(View v) {
        Intent nextPageIntent = new Intent(this, CategoryActivity.class);
        startActivity(nextPageIntent);
        // Il faut finish l'ancienne activity.
        // Vous empilez les vues sans jamais les détruires.
    }

    public void onChangeTimerFive(View v)
    {
        GameData.TimerValue = 5000;
    }

    public void onChangeTimerThirty(View v)
    {
        GameData.TimerValue = 30000;
    }

    public void onChangeTimerNinety(View v)
    {
        GameData.TimerValue = 90000;
    }

}
