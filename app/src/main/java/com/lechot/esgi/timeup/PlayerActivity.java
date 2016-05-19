package com.lechot.esgi.timeup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by Bastien on 18/05/2016.
 */
public class PlayerActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ListViewCompat playerListView;
    private EditText playerEditText;
    private Button playerAddButton;
    private PlayersAdapter playersAdapter;
    private CheckBox editModeCheckbox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        playerListView = (ListViewCompat) findViewById(R.id.player_list_view);
        playerEditText = (EditText) findViewById(R.id.player_edit_text);
        playerAddButton = (Button) findViewById(R.id.button_add_player);
        editModeCheckbox = (CheckBox) findViewById(R.id.edition_checkbox);

        if (playerAddButton != null){
            playerAddButton.setOnClickListener(this);
        }

        if (editModeCheckbox != null){
            editModeCheckbox.setOnCheckedChangeListener(this);
        }

        playersAdapter = new PlayersAdapter(this);
        playerListView.setAdapter(playersAdapter);
        playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Player player = playersAdapter.getItem(position);
                Toast.makeText(PlayerActivity.this, "Player clicked : " + player.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        String playerName = playerEditText.getText().toString();
        if (!playerName.isEmpty()){
            playersAdapter.add(new Player(playerName));
            playerEditText.getText().clear();
        }

        for (int i = 0; i < playersAdapter.getCount(); i++){
            playersAdapter.getItem(i);
        }

    }

    public void onEndListClicked(View v) {
        // Explicit Intent ---> I want to launch the ExampleActivity
        Bundle b = new Bundle();
        ArrayList<String> Team1 = new ArrayList<String>();
        ArrayList<String> Team2 = new ArrayList<String>();
        for (int i = 0; i < playersAdapter.getCount(); i++){
            if(Team1.size()>Team2.size()){
                Team2.add(playersAdapter.getItem(i).getName());
            }
            else if (Team2.size()>Team1.size()){
                Team1.add(playersAdapter.getItem(i).getName());
            }
            else{
                double parm = Math.random();
                if (parm > 0.5)
                    Team1.add(playersAdapter.getItem(i).getName());
                else
                    Team2.add(playersAdapter.getItem(i).getName());
            }
        }
        Intent nextPageIntent = new Intent(this, TeamViewActivity.class);
        nextPageIntent.putStringArrayListExtra("team1", Team1);
        nextPageIntent.putStringArrayListExtra("team2", Team2);
        startActivity(nextPageIntent);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        playersAdapter.setEditMode(isChecked);
    }
}
