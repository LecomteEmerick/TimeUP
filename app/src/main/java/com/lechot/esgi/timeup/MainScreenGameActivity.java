package com.lechot.esgi.timeup;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    private TextView Timer;
    private TextView Word;
    private TextView StateDescription;
    private TextView TeamAScoreView;
    private TextView TeamBScoreView;

    private CountDownTimer FinalCountDown;

    private LinearLayout TeamAScoreLayout;
    private LinearLayout TeamBScoreLayout;

    private ArrayList<String> randomWords;
    private String[] wordsReferences = new String[] { "Teemo", "Toto", "Tata", "Tutu", "Turlututu", "tetu" };

    private int WordIndex=0;
    private boolean GameStarted = false;

    private int TeamAScore;
    private int TeamBScore;
    private boolean isTeamAPlaying=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_game);

        this.TeamAScore = 0;
        this.TeamBScore = 0;

        this.Timer = (TextView)findViewById(R.id.TimerText);
        this.Word = (TextView)findViewById(R.id.HiddenWord);
        this.StateDescription = (TextView)findViewById(R.id.actionText);

        this.TeamAScoreView = (TextView) findViewById(R.id.ScoreTeamA);
        this.TeamBScoreView = (TextView) findViewById(R.id.ScoreTeamB);

        this.TeamAScoreLayout = (LinearLayout) findViewById(R.id.ScorePanelA);
        this.TeamBScoreLayout = (LinearLayout) findViewById(R.id.ScorePanelB);

        this.TeamAScoreView.setText(Integer.toString(this.TeamAScore));
        this.TeamBScoreView.setText(Integer.toString(this.TeamBScore));
        this.Word.setText("");

        this.FinalCountDown = new CountDownTimer(30000,1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                Timer.setText(String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)));
            }

            @Override
            public void onFinish() {
                Timer.setText("Time's Up !");
                StopGame();
            }
        };

        StartGame();

    }

    public void CreateRandomListWord()
    {
        this.randomWords = new ArrayList<String>();
        for(int i=0; i < this.wordsReferences.length; ++i)
            this.randomWords.add(this.wordsReferences[i]);
        Collections.shuffle(this.randomWords);
    }

    public void StartGame()
    {
        isTeamAPlaying = !isTeamAPlaying;

        GameStarted = true;
        WordIndex = 0;
        CreateRandomListWord();
        this.FinalCountDown.start();
        this.Word.setText(this.randomWords.get(WordIndex));
    }

    public void StopGame()
    {
        GameStarted = false;
        this.FinalCountDown.cancel();
        this.Word.setText("");
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
        this.Word.setText(this.randomWords.get(WordIndex));
    }

    public void FoundWord(View v)
    {
        if(!GameStarted)
            return;

        WordIndex = (WordIndex + 1) % this.randomWords.size();
        this.Word.setText(this.randomWords.get(WordIndex));

        if(isTeamAPlaying) {
            ++this.TeamAScore;
            this.TeamAScoreView.setText(Integer.toString(this.TeamAScore));
        }

        else {
            ++this.TeamBScore;
            this.TeamBScoreView.setText(Integer.toString(this.TeamBScore));
        }
    }
}
