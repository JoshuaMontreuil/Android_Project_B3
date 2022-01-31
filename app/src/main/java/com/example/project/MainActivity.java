package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton start = null;

    private EditText username;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start_button);
        username = (EditText) findViewById(R.id.editUsername);
        prefs = getSharedPreferences("MY_PREFS_NAME",MODE_PRIVATE);
        editor = prefs.edit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        editor.putString("NAME", username.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (username.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter your Username", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MainActivity.this,MemoryActivity.class);
            startActivity(intent);
        }
    }
}