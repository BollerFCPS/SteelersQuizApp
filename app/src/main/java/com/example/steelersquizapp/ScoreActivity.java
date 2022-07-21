package com.example.steelersquizapp;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreNumTV, highScoresTV, namer0, scorer0, namer1, scorer1, namer2, scorer2, namer3, scorer3, namer4, scorer4;
    ImageView logoIV;
    Button emailBut, hsButton;
    TextView scoreHeaderTV;
    String name;

    static final String TAG = "AAAAAA";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://quizappdb-cb59c-default-rtdb.firebaseio.com/");
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    /*
    DatabaseReference Place1 = database.getReference("Place1");
    DatabaseReference Place2 = database.getReference("Place2");
    DatabaseReference Place3 = database.getReference("Place3");
    DatabaseReference Place4 = database.getReference("Place4");
    DatabaseReference Place5 = database.getReference("Place5");
    DatabaseReference myRef = database.getReference("Current");
    */


    private SharedPreferences mPreferences;
    private String sharedPrefFile = "SteelerPrefs";
    private final String NAME_KEY = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreNumTV = (TextView) findViewById(R.id.scoreNumTV);
        //logoIV = (ImageView) findViewById(R.id.logoIV);
        emailBut = (Button) findViewById(R.id.emailButton);
        scoreHeaderTV = (TextView) findViewById(R.id.scoreHeader);
        //highScoresTV = (TextView) findViewById(R.id.highScoreTV);
        hsButton = (Button) findViewById(R.id.hsButton);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        namer0 = (TextView) findViewById(R.id.namer0);
        scorer0 = (TextView) findViewById(R.id.scorer0);
        namer1 = (TextView) findViewById(R.id.namer1);
        scorer1 = (TextView) findViewById(R.id.scorer1);
        namer2 = (TextView) findViewById(R.id.namer2);
        scorer2 = (TextView) findViewById(R.id.scorer2);
        namer3= (TextView) findViewById(R.id.namer3);
        scorer3 = (TextView) findViewById(R.id.scorer3);
        namer4= (TextView) findViewById(R.id.namer4);
        scorer4 = (TextView) findViewById(R.id.scorer4);

        name = mPreferences.getString(NAME_KEY, "User");

        scoreHeaderTV.setText(name +"'s "+ getString(R.string.scoreHead));

        Intent incomingIntent = getIntent();
        int score = incomingIntent.getIntExtra("scoreName",0 );
        scoreNumTV.setText("" + score);

        ScoreObject currentScore = new ScoreObject(name, score);
        ArrayList<ScoreObject> highScores = new ArrayList<ScoreObject>();
        String value = "";
        mDatabase.child("Current").setValue(currentScore.toString());

        for (int i = 1; i <= 5; i++){
        mDatabase.child("Place"+i).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                int spa = value.indexOf(" ");
                highScores.add(new ScoreObject(value.substring(spa+1),parseInt(value.substring(0,spa))));
                Log.d(TAG, "Value is: " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        }); }

        /*
        mDatabase.child("Place2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                highScores.add(new ScoreObject(value.substring(3),parseInt(value.substring(0,2))));
                Log.d(TAG, "Value is: " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        mDatabase.child("Place3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                highScores.add(new ScoreObject(value.substring(3),parseInt(value.substring(0,2))));
                Log.d(TAG, "Value is: " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        mDatabase.child("Place4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                highScores.add(new ScoreObject(value.substring(3),parseInt(value.substring(0,2))));
                Log.d(TAG, "Value is: " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        mDatabase.child("Place5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                highScores.add(new ScoreObject(value.substring(3),parseInt(value.substring(0,2))));
                Log.d(TAG, "Value is: " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

         */

        hsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highScores.add(currentScore);
                Collections.sort(highScores);


        for(int j = 1; j<=5; j++){
            mDatabase.child("Place"+j).setValue(highScores.get(j-1).toString());
        }

            namer0.setText(highScores.get(0).getName());
            scorer0.setText(""+highScores.get(0).getScore());
            namer1.setText(highScores.get(1).getName());
            scorer1.setText(""+highScores.get(1).getScore());
            namer2.setText(highScores.get(2).getName());
            scorer2.setText(""+highScores.get(2).getScore());
            namer3.setText(highScores.get(3).getName());
            scorer3.setText(""+highScores.get(3).getScore());
            namer4.setText(highScores.get(4).getName());
            scorer4.setText(""+highScores.get(4).getScore());



            }
        });



        emailBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subj = getString(R.string.emailSubject);
                String bod = getString(R.string.emailBody1) + score + getString(R.string.emailBody2);
                composeEmail(subj, bod);
            }
        });
    }

    public void composeEmail(String subject, String body) {
        Intent sendIntent = new Intent();
        sendIntent.setData(Uri.parse("mailto:")); // only email apps should handle this
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendIntent.putExtra(Intent.EXTRA_TEXT, body);

        // Try to invoke the intent.
        try {
            startActivity(sendIntent);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }


        /*Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        */

    }

}