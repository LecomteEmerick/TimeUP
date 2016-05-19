package com.lechot.esgi.timeup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bastien on 19/05/2016.
 */
public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ArrayList<String> TeamA = new ArrayList<>();
        ArrayList<String> TeamB = new ArrayList<>();
        for (int i = 0; i < GameData.TeamA.size(); i++) {
            TeamA.add(GameData.TeamA.get(i).getName());
            TeamB.add(GameData.TeamB.get(i).getName());
        }
        ArrayAdapter<String> itemsAdapterTeamA =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TeamA);
        ArrayAdapter<String> itemsAdapterTeamB =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, TeamB);

        ArrayAdapter<Integer> scoreTeamA =
                new ArrayAdapter<Integer>(this, GameData.TeamAScore);
        ArrayAdapter<Integer> scoreTeamB =
                new ArrayAdapter<Integer>(this, GameData.TeamBScore);

        ListView listViewTeamA = (ListView) findViewById(R.id.listViewTeamA);
        listViewTeamA.setAdapter(itemsAdapterTeamA);
        ListView listViewTeamB = (ListView) findViewById(R.id.listViewTeamB);
        listViewTeamB.setAdapter(itemsAdapterTeamB);

        TextView tA = (TextView) findViewById(R.id.ScoreTeamA);
        tA.setText("Team A : " + GameData.TeamAScore);
        TextView tB = (TextView) findViewById(R.id.ScoreTeamB);

        tB.setText("Team B : " + GameData.TeamBScore);

        if (GameData.TeamAScore > GameData.TeamBScore) {
            TextView tWin = (TextView) findViewById(R.id.Winner);
            tWin.setText("Gagnant : Team A");
        } else if (GameData.TeamBScore > GameData.TeamAScore) {
            TextView tWin = (TextView) findViewById(R.id.Winner);
            tWin.setText("Gagnant : Team B");
        } else{
            TextView tWin = (TextView) findViewById(R.id.Winner);
            tWin.setText("Gagnant : Egalité");
    }

    }
    public void onNextRoundClicked(View v) {
        Intent nextPageIntent = new Intent(this, CategoryActivity.class);
        startActivity(nextPageIntent);
    }
    public void onNewGmeClicked(View v) {
        GameData.TeamAScore = 0;
        GameData.TeamBScore = 0;
        Intent nextPageIntent = new Intent(this, CategoryActivity.class);
        startActivity(nextPageIntent);
    }

}
