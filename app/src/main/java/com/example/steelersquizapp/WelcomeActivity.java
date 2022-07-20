package com.example.steelersquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    TextView myTextView;
    EditText myEditText;
    Button submitButton;
    ImageView bannerIV;
    String name;

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "SteelerPrefs";
    private final String NAME_KEY = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        myTextView = (TextView) findViewById(R.id.greeting);
        myEditText = (EditText) findViewById(R.id.typeName);
        submitButton = (Button) findViewById(R.id.submitButton);
        bannerIV = (ImageView) findViewById(R.id.logoIV);

        name = "";

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = myEditText.getText().toString();
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();

                //Write the id of the selected button to our SharedPreferences file
                //this is an int Key/Value pair where the:
                //key = COLOR_KEY = "COLOR"
                //value = view.getID() = "red_button", "blue_button", etc.
                preferencesEditor.putString(NAME_KEY, name);

                //Commit the value and save the file.
                preferencesEditor.apply();

                Intent runQuiz = new Intent(WelcomeActivity.this, QuizActivity.class);
                startActivity(runQuiz);
            }
        });
    }
}
