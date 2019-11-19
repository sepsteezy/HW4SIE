package com.example.hw4sie;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonSearch;
    EditText editTextSearchZipcode;
    TextView textViewFoundBirdBirdName, textViewFoundBirdPersonName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//Standard stuff
        buttonSearch = findViewById(R.id.buttonSearchSubmit);

        editTextSearchZipcode = findViewById(R.id.editTextSearchZipcode);

        textViewFoundBirdBirdName = findViewById(R.id.textViewSearchFoundBirdBirdName);
        textViewFoundBirdPersonName = findViewById(R.id.textViewSearchFoundBirdPersonName);

        buttonSearch.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
  //menu creation
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View view) {
//firebase db
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("myBirds");

        // Sets searchedZipcode to the value entered when users hit the button
        if (view == buttonSearch) {
            Integer searchedZipcode = Integer.parseInt(editTextSearchZipcode.getText().toString());

            //Firebase magic of sorting by zipcode and returning values of the most recent bird object created at the given zipcode
            myRef.orderByChild("zipcode").equalTo(searchedZipcode).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    //String findKey = dataSnapshot.getKey();
                    Bird foundBird = dataSnapshot.getValue(Bird.class);
                    String findBirdBirdName = foundBird.birdName;
                    String findBirdPersonName = foundBird.personName;

                    textViewFoundBirdBirdName.setText(findBirdBirdName);
                    textViewFoundBirdPersonName.setText(findBirdPersonName);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //menu navigation
        if (item.getItemId() == R.id.Home) {

            Intent homeIntent = new Intent(this, MainActivity.class);
            startActivity(homeIntent);

        } else if (item.getItemId() == R.id.SearchPage) {

            Toast.makeText(this, "You are already on the Search Page", Toast.LENGTH_SHORT).show();
        }
            return super.onOptionsItemSelected(item);
        }

}
