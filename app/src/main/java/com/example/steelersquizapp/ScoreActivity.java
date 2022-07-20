package com.example.steelersquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreNumTV;
    ImageView logoIV;
    Button emailBut;
    TextView scoreHeaderTV;
    String name;

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "SteelerPrefs";
    private final String NAME_KEY = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreNumTV = (TextView) findViewById(R.id.scoreNumTV);
        logoIV = (ImageView) findViewById(R.id.logoIV);
        emailBut = (Button) findViewById(R.id.emailButton);
        scoreHeaderTV = (TextView) findViewById(R.id.scoreHeader);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        name = mPreferences.getString(NAME_KEY, "User");

        scoreHeaderTV.setText(name +"'s "+ getString(R.string.scoreHead));



        Intent incomingIntent = getIntent();
        int score = incomingIntent.getIntExtra("scoreName",0 );
        scoreNumTV.setText("" + score);

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