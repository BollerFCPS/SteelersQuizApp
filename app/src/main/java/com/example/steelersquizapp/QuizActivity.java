package com.example.steelersquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuizActivity extends AppCompatActivity {

    //declare variable for your UI elements
    TextView qTextTV;
    Button tBut, fBut, scoreBut, nextBut;
    String message;
    int score, curIndex;
    question q1, q2, q3, q4, q5, curQ;
    question[] questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //initialize / inflate your UI elements
        qTextTV = (TextView) findViewById(R.id.questionText);
        tBut = (Button) findViewById(R.id.trueButton);
        fBut = (Button) findViewById(R.id.falseButton);
        nextBut = (Button) findViewById(R.id.nextButton);
        scoreBut = (Button) findViewById(R.id.scoreButton);
        score = 0;
        q1 = new question(getString(R.string.q1Text), false);
        q2 = new question(getString(R.string.q2Text), true);
        q3 = new question(getString(R.string.q3Text), false);
        q4 = new question(getString(R.string.q4Text), true);
        q5 = new question(getString(R.string.q5Text), true);
        questions = new question[] {q1, q2, q3, q4, q5};
        curIndex = 0;
        curQ = questions[curIndex];
        qTextTV.setText(curQ.getQText());
        //do something with the UI elements
        tBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curQ.getCorAns()){
                    message = getString(R.string.Woohoo);
                    score++;
                }
                else{
                    message = getString(R.string.WrongAns);}

                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, message, duration);
                toast.show();
            }
        });

        fBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (curQ.getCorAns()){
                    message = getString(R.string.WrongAns);
                }
                else{
                    message = getString(R.string.Woohoo);
                    score++;
                }
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

        nextBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curIndex++;
                if(curIndex < questions.length) {
                    curQ = questions[curIndex];
                    String tempQText = curQ.getQText();
                    qTextTV.setText(tempQText);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.noMoreQ, Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }
}