package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class EndActivity extends AppCompatActivity implements View.OnClickListener{

    protected ImageButton to_HomePage;
    protected ImageButton restart_game;
    protected SharedPreferences prefs;
    protected MediaPlayer victoryMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        to_HomePage = findViewById(R.id.HomeButton);
        restart_game = findViewById(R.id.RestartButton);
        prefs = getSharedPreferences("MY_PREFS_NAME",MODE_PRIVATE);
        victoryMusic = MediaPlayer.create(this, R.raw.victory);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ChronoFragment fragmentChrono = (ChronoFragment) getSupportFragmentManager().findFragmentById(R.id.ChronoScore);
        //Gson gson = new Gson();
        String json = prefs.getString("LIST","");
        //List<String> list = gson.fromJson(json, new TypeToken<ArrayList<String>>(){}.getType());

        /*
        for(int i = 0; i < list.size(); i++){
            ScoreBoardFragment fragment = new ScoreBoardFragment(list.get(i),list.get(i),list.get(i));
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        to_HomePage.setOnClickListener(this);
        restart_game.setOnClickListener(this);
        victoryMusic.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        victoryMusic.pause();
    }

    @Override
    public void onClick(View view){
        int ID = view.getId();
        Intent intent;

        if(ID == to_HomePage.getId()){
            intent = new Intent(EndActivity.this,MainActivity.class);
        }else{
            intent = new Intent(EndActivity.this,MemoryActivity.class);
        }
        startActivity(intent);
    }
}