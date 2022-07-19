package com.example.steelersquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    //declare variable for your UI elements
    TextView qText;
    Button tBut, fBut, scoreBut;
    String message;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //initialize / inflate your UI elements
        qText = (TextView) findViewById(R.id.questionText);
        tBut = (Button) findViewById(R.id.trueButton);
        fBut = (Button) findViewById(R.id.falseButton);
        scoreBut = (Button) findViewById(R.id.scoreButton);
        score = 0;

        //do something with the UI elements
        tBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "You got it wrong.";
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, message, duration);
                toast.show();
            }
        });

        fBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "Woohoo! That's it.";
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                score += 1;
                Toast toast = Toast.makeText(context, message, duration);
                toast.show();
            }
        });

        scoreBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showScore = new Intent(QuizActivity.this, ScoreActivity.class);
                showScore.putExtra("scoreName", score);
                startActivity(showScore);
            }
        });
    }
}