package com.example.frontendexperiment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);

    }

    public void login(View v) {
        if (username.getText().toString().equals(Utility.username) && password.getText().toString().equals(Utility.password)) { //correct username/password
            Toast.makeText(getApplicationContext(), "Correct Credentials", Toast.LENGTH_SHORT).show();
            Intent page2 = new Intent(this, MainActivity3.class);
            page2.putExtra("username", (username.getText().toString()));
            page2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(page2);
//            finish();
        }
        else { //wrong username/password
            Toast.makeText(getApplicationContext(), "Wrong Credentials!", Toast.LENGTH_SHORT).show();
        }
    }

}