package com.example.steelersquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreNumTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreNumTV = (TextView) findViewById(R.id.scoreNumTV);

        Intent incomingIntent = getIntent();
        int score = incomingIntent.getIntExtra("scoreName",0 );
        scoreNumTV.setText("" + score);

    }
}