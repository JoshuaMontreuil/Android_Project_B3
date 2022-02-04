package com.example.project;

import static java.util.Collection.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.text.Collator;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EndActivity extends AppCompatActivity implements View.OnClickListener{

    protected ImageButton to_HomePage;
    protected ImageButton restart_game;
    protected SharedPreferences prefs;
    protected  List<Score> scores_list;

    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        to_HomePage = findViewById(R.id.HomeButton);
        restart_game = findViewById(R.id.RestartButton);
        prefs = getSharedPreferences("MY_PREFS_NAME",MODE_PRIVATE);
        editor = prefs.edit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Get scores
        Gson gson = new Gson();
        String json_scores = prefs.getString("SCORES","");
        System.out.println(json_scores);
        scores_list = gson.fromJson(json_scores,new TypeToken<ArrayList<Score>>(){}.getType());
        ChronoFragment fragmentChrono = (ChronoFragment) getSupportFragmentManager().findFragmentById(R.id.ChronoScore);
        fragmentChrono.setText(scores_list.get(scores_list.size()-1).getTime());

        for(int i = 0; i < scores_list.size(); i++){
            ScoreBoardFragment fragment = new ScoreBoardFragment(scores_list.get(i).getUsername(),scores_list.get(i).getTime()," 1");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ScoreBoard, fragment)
                    .commit();
        }
        System.out.println("FIN : " + scores_list );


    }

    @Override
    protected void onResume() {
        super.onResume();
        to_HomePage.setImageResource(R.drawable.home);
        restart_game.setImageResource(R.drawable.restart);

        to_HomePage.setOnClickListener(this);
        restart_game.setOnClickListener(this);
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