package com.example.hw4sie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //create objects
    Button buttonSubmit;
    EditText editTextBirdName, editTextZipcode, editTextPersonName;
    Integer zipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link objects
        buttonSubmit = findViewById(R.id.buttonSubmit);

        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextZipcode = findViewById(R.id.editTextZipcode);
        editTextPersonName = findViewById(R.id.editTextPersonName);

        // set up listeners
        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSubmit){
            String birdName = editTextBirdName.getText().toString();
            String personName = editTextPersonName.getText().toString();
            String zipcode = editTextZipcode.getText().toString();

            Bird createBird = new Bird(birdName, zipcode, personName);
        }

    }
}
