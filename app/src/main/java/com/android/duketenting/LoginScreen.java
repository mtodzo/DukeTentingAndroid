package com.android.duketenting;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               // String value = dataSnapshot.getValue(String.class);
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Toast.makeText(LoginScreen.this, child.getKey(), Toast.LENGTH_LONG);
                }
                //Log.d("tag", "Value is: " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("tag", "Failed to read value.", error.toException());
            }
        });
    }

    public void onLogin(View view){
        EditText usernameEditText = findViewById(R.id.editText);
        EditText passwordEditText = findViewById(R.id.passwordText);
        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        myRef.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    if (((HashMap)child.getValue()).get("userName").equals(username) && ((HashMap)child.getValue()).get("password").equals(password)){
                        Toast myToast = Toast.makeText(LoginScreen.this, "Successful Login", Toast.LENGTH_SHORT);
                        myToast.show();
                        //Log.d("successful login", "YEEHAW");
                        return;
                    }
                }
                Log.d("unsuccessful login", "incorrect username or password");
            }
            @Override
            public void onCancelled(DatabaseError error){
                //oh nooo!
            }
        });

    }

    public void onCreateAccount(View view){
        EditText usernameEditText = findViewById(R.id.editText);
        EditText passwordEditText = findViewById(R.id.passwordText);
        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        myRef.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    if (((HashMap)child.getValue()).get("userName").equals(username)){
                        Log.d("username clash", username);
                        return;
                    }
                }
                addUser(username, password);
            }
            @Override
            public void onCancelled(DatabaseError error){
                //oh nooo!
            }
        });

    }

    private void addUser(String username, String password) {
        //Map<String, String> userMap = new HashMap<>();
        User newUser = new User(username, password);
        //userMap.put(username, password);
        //myRef.child("Users").push().setValue(newUser);
        String key = myRef.child("Users").push().getKey();
        myRef.child("Users").child(key).setValue(newUser);
        newUser.setID(key);
    }
}
