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
import com.lechot.esgi.timeup.GameData;

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
        for (int i = 0; i < GameData.PlayerList.size(); i++){
            if(i==0)
                GameData.TeamA.add((GameData.PlayerList.get(i)));
            if(GameData.TeamA.size()> GameData.TeamB.size()){
                GameData.TeamB.add((GameData.PlayerList.get(i)));
            }
            else if ( GameData.TeamB.size()>GameData.TeamA.size()){
                GameData.TeamA.add((GameData.PlayerList.get(i)));
            }
            else{
                double param = Math.random();
                if (param > 0.5)
                    GameData.TeamA.add((GameData.PlayerList.get(i)));
                else
                    GameData.TeamB.add((GameData.PlayerList.get(i)));
            }
        }
        Intent nextPageIntent = new Intent(this, TeamViewActivity.class);
        startActivity(nextPageIntent);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        playersAdapter.setEditMode(isChecked);
    }
}
