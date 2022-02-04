package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton start = null;
    private EditText username;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private MediaPlayer backgroundMusic;
    private MediaPlayer startTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start_button);
        username = (EditText) findViewById(R.id.editUsername);
        prefs = getSharedPreferences("MY_PREFS_NAME",MODE_PRIVATE);
        editor = prefs.edit();
        backgroundMusic = MediaPlayer.create(this, R.raw.background);
        startTransition = MediaPlayer.create(this, R.raw.start);
    }

    @Override
    protected void onResume() {
        super.onResume();
        start.setOnClickListener(this);
        backgroundMusic.start();
    }

    protected void onPause(){
        super.onPause();
        backgroundMusic.pause();
    }

    @Override
    public void onClick(View v) {
        if (username.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter your Username", Toast.LENGTH_SHORT).show();
        } else {
           String current_user = username.getText().toString();
            //Save current username
            editor.putString("CURRENT_USERNAME", current_user);
            editor.apply();
            Intent intent = new Intent(MainActivity.this,MemoryActivity.class);
            startActivity(intent);
            startTransition.start();
        }
    }
}