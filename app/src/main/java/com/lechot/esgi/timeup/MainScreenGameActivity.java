package com.lechot.esgi.timeup;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Created by Emerick Lecomte on 18/05/2016.
 */
public class MainScreenGameActivity extends AppCompatActivity{

    // peut importe la convention mais une unité quand même. Majuscule, sans majuscule, mais on s'y tient.
    private int EtapeNumber = 1;

    private int NextPlayerTeamAId = 0;
    private int NextPlayerTeamBId = 0;
    private boolean AllPlayerInTeamAHavePlayed = false;
    private boolean AllPlayerInTeamBHavePlayed = false;

    private TextView Timer;
    private TextView Word;
    private TextView StateDescription;
    private TextView TeamAScoreView;
    private TextView TeamBScoreView;
    private TextView TeamPlayInfosView;

    private CountDownTimer FinalCountDown;
    private CountDownTimer SwitchTeamCountDown;

    private LinearLayout TeamAScoreLayout;
    private LinearLayout TeamBScoreLayout;

    private ArrayList<Words> randomWords;
    //private String[] wordsReferences = new String[] { "Teemo", "Toto", "Tata", "Tutu", "Turlututu", "tetu" };

    private int WordIndex=0;
    private boolean GameStarted = false;

    private boolean isTeamAPlaying=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_game);

        this.NextPlayerTeamAId = 0;
        this.NextPlayerTeamBId = 0;


        this.Timer = (TextView)findViewById(R.id.TimerText);
        this.Word = (TextView)findViewById(R.id.HiddenWord);
        this.StateDescription = (TextView)findViewById(R.id.actionText);

        this.TeamAScoreView = (TextView) findViewById(R.id.ScoreTeamA);
        this.TeamBScoreView = (TextView) findViewById(R.id.ScoreTeamB);

        this.TeamAScoreLayout = (LinearLayout) findViewById(R.id.ScorePanelA);
        this.TeamBScoreLayout = (LinearLayout) findViewById(R.id.ScorePanelB);

        this.TeamPlayInfosView = (TextView) findViewById(R.id.TeamPlayInfos);

        this.TeamAScoreView.setText(Integer.toString(GameData.TeamAScore));
        this.TeamBScoreView.setText(Integer.toString(GameData.TeamBScore));
        this.Word.setText("");

        Timer.setText(String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(GameData.TimerValue))); // Format des nombres. ( virgule en français, point en anglais)

        this.FinalCountDown = new CountDownTimer(GameData.TimerValue,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                Timer.setText(String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)));
            }

            @Override
            public void onFinish() {
                Timer.setText("Time's Up !");
                SwitchTeamCountDown.start();
                StopGame();
            }
        };

        this.SwitchTeamCountDown = new CountDownTimer(1000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                StartGame();
            }
        };

        WordIndex = 0;
        CreateRandomListWord();

        StartGame();

    }

    public void SetStateDescription()
    {
        switch(this.EtapeNumber)
        {
            case 1:
                StateDescription.setText("Règle : mots illimité."); // Dans les strings
                break;
            case 2:
                StateDescription.setText("Règle : un seul mot.");
                break;
            case 3:
                StateDescription.setText("Règle : mime.");
                break;
        }
    }

    public void CreateRandomListWord()
    {
        String[] wordsReferences;
        switch(GameData.SelectedCategories)
        {
            case Animals:
                wordsReferences = GameData.AnimalsWords;
                break;
            case Celebrety:
                wordsReferences = GameData.CelebretyWords;
                break;
            case Games:
                wordsReferences = GameData.GamesWords;
                break;
            default:
                wordsReferences = GameData.HistoryWords;
                break;
        }

        this.randomWords = new ArrayList<Words>();
        for(int i=0; i < wordsReferences.length; ++i) // Pour faire une copy : randomWords = new ArrayList<>(wordReferences); et voilà :P
            this.randomWords.add(new Words(wordsReferences[i]));

        Collections.shuffle(this.randomWords);
    }

    public void StartGame()
    {
        isTeamAPlaying = !isTeamAPlaying;
        String team;
        if(isTeamAPlaying)
            team = "A \nJoueur " + GameData.TeamA.get(NextPlayerTeamAId).getName();
        else
            team = "B \nJoueur " + GameData.TeamB.get(NextPlayerTeamBId).getName();

        TeamPlayInfosView.setText("Equipe " + team);

        SetStateDescription();

        new AlertDialog.Builder(MainScreenGameActivity.this)
                .setTitle("Nouveau Tour")
                .setMessage("Equipe " + team)

                // Ok oui en effet, pour gérer le bouton back. Cela dit vous auriez pu forcer la validation via Ok :
                // .setCancelable(false)
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialog)
                    {
                        GameStarted = true;
                        FinalCountDown.start();
                        Word.setText(randomWords.get(WordIndex).Word);
                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        GameStarted = true;
                        FinalCountDown.start();
                        Word.setText(randomWords.get(WordIndex).Word);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void StopGame() // Oui mais attention dans les régles la manche 1 continue tant que tous les mots ne sont pas trouvé. En alternant Team A Team B
    { // Mais vous avez géré le cas des teams avec un nombre de personnes différentes
        GameStarted = false;
        this.FinalCountDown.cancel();
        this.Word.setText("Tour Terminé.");

        if(isTeamAPlaying) {
            ++NextPlayerTeamAId;
            if(NextPlayerTeamAId == GameData.TeamA.size())
                AllPlayerInTeamAHavePlayed =true;

            NextPlayerTeamAId %= GameData.TeamA.size();
        }else {
            ++NextPlayerTeamBId;
            if(NextPlayerTeamBId == GameData.TeamB.size())
                AllPlayerInTeamBHavePlayed =true;

            NextPlayerTeamBId %= GameData.TeamB.size();
        }

        if(AllPlayerInTeamAHavePlayed && AllPlayerInTeamBHavePlayed)
        {
            SwitchTeamCountDown.cancel();

            isTeamAPlaying = false;

            NextPlayerTeamAId = 0;
            NextPlayerTeamBId = 0;

            AllPlayerInTeamAHavePlayed = false;
            AllPlayerInTeamBHavePlayed = false;

            ++EtapeNumber;
            if(EtapeNumber < 4) {
                SetNextEtape();
                return;
            }else{
                FinishGame(null);
            }
        }

    }

    public void ShowScore(View v)
    {
        TeamAScoreLayout.setVisibility(TeamAScoreLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        TeamBScoreLayout.setVisibility(TeamBScoreLayout.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }

    public void SkipWord(View v)
    {
        if(!GameStarted)
            return;

        WordIndex = (WordIndex + 1) % this.randomWords.size();
        int index = GetNextWordIndex();

        if(index != this.randomWords.size()) {
            this.Word.setText(this.randomWords.get(WordIndex).Word);
        }

        if(isTeamAPlaying) {
            ++GameData.TeamA.get(NextPlayerTeamAId).SkipAnswerScore;
        }

        else {
            ++GameData.TeamB.get(NextPlayerTeamBId).SkipAnswerScore;
        }
    }

    public void FoundWord(View v)
    {
        if(!GameStarted)
            return;

        this.randomWords.get(WordIndex).isFound = true;

        if(isTeamAPlaying) {
            ++GameData.TeamAScore;
            this.TeamAScoreView.setText(Integer.toString(GameData.TeamAScore));
            ++GameData.TeamA.get(NextPlayerTeamAId).RightAnswerScore;
        }

        else {
            ++GameData.TeamBScore;
            this.TeamBScoreView.setText(Integer.toString(GameData.TeamBScore));
            ++GameData.TeamB.get(NextPlayerTeamBId).RightAnswerScore;
        }

        int index = GetNextWordIndex();

        if(index == this.randomWords.size())
        {
            StopGame();
            ++EtapeNumber;
            if(EtapeNumber < 4) {
                SetNextEtape();
                return;
            }else{
                FinishGame(v);
            }
        }

        this.Word.setText(this.randomWords.get(WordIndex).Word);
    }

    public int GetNextWordIndex()
    {
        int i=0;
        while(this.randomWords.get(WordIndex).isFound && i < this.randomWords.size())
        {
            WordIndex = (WordIndex + 1) % this.randomWords.size();
            ++i;
        }
        return i;
    }

    public void SetNextEtape()
    {
        NextPlayerTeamAId = 0;
        NextPlayerTeamBId = 0;

        //StopGame();

        WordIndex=0;
        ArrayList<Words> newWordList = new ArrayList<Words>();
        for(Words w : this.randomWords)
        {
            if(w.isFound)
            {
                w.isFound = false;
                newWordList.add(w);
            }
        }
        if(newWordList.size() > 0)
            this.randomWords = newWordList;

        SwitchTeamCountDown.start();
    }

    public void FinishGame(View v)
    {
        StopGame();

        onSwitchToScore(v);
    }
    public void onSwitchToScore(View v){
        // Explicit Intent ---> I want to launch the ExampleActivity
        Intent nextPageIntent = new Intent(this, ScoreActivity.class);
        startActivity(nextPageIntent);
    }
}
