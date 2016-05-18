package com.lechot.esgi.timeup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onPlayClicked(View v){
        // Explicit Intent ---> I want to launch the ExampleActivity
        Intent nextPageIntent = new Intent(this, PlayerActivity.class);
        startActivity(nextPageIntent);

        // close this activity after launching the ExampleActivity
        finish();
    }
}
