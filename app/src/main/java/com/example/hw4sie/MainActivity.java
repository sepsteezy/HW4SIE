package com.example.hw4sie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //create objects
    Button buttonSubmit;
    EditText editTextBirdName, editTextZipcode, editTextPersonName;

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
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("myBirds");

        if (view == buttonSubmit) {
            String createBirdName = editTextBirdName.getText().toString();
            String createPersonName = editTextPersonName.getText().toString();
            String createZipcode = editTextZipcode.getText().toString();

            Bird createBird = new Bird(createBirdName, createZipcode, createPersonName);

            myRef.push().setValue(createBird);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.SearchPage) {
            Intent searchIntent = new Intent(this, SearchActivity.class );
            startActivity(searchIntent);

        } else if (item.getItemId() == R.id.Home) {
            Toast.makeText(this, "You are already on the Home Page", Toast.LENGTH_SHORT).show();
        }
            return super.onOptionsItemSelected(item);
    }
}
