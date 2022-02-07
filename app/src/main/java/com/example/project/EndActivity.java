package com.example.project;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EndActivity extends AppCompatActivity implements View.OnClickListener{

    protected ImageButton to_HomePage;
    protected ImageButton restart_game;
    protected SharedPreferences prefs;
    protected  List<Score> scores_list;

    private SharedPreferences.Editor editor;
    boolean lastPlayer_ScoreBoard; // variable pour savoir si le joueur est dans le score board ou pas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        to_HomePage = findViewById(R.id.HomeButton);
        restart_game = findViewById(R.id.RestartButton);
        prefs = getSharedPreferences("MY_PREFS_NAME",MODE_PRIVATE);
        editor = prefs.edit();
        lastPlayer_ScoreBoard = true;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onStart() {
        super.onStart();
        //Get scores
        Gson gson = new Gson();
        String json_scores = prefs.getString("SCORES","");
        scores_list = gson.fromJson(json_scores,new TypeToken<ArrayList<Score>>(){}.getType());
        /*
           Boucle permettant de :
                - ajouter un id a chaque joueur
                - convertir le temps (string) en score (int) pour pouvoir trier les joueurs pour le leader board
         */
        for(int i = 0; i < scores_list.size(); i++){
            scores_list.get(i).setId(i);
            String temps_int = scores_list.get(i).getTime();
            temps_int = temps_int.replace(":", "");
            int temps = Integer.parseInt(temps_int);
            scores_list.get(i).setScore(temps);
        }
        // recuperer le dernier joueur inscrit pour ensuite gérer l'affichage
        int last_player_id = scores_list.get(scores_list.size()-1).getId();

        /*
            Attribuer les postions des joueurs une fois que le tableau est trié en fonction des scores
         */

        // affichage du chrono en gros du dernier joueur
        ChronoFragment fragmentChrono = (ChronoFragment) getSupportFragmentManager().findFragmentById(R.id.ChronoScore);
        fragmentChrono.setText(scores_list.get(last_player_id).getTime());

        /*
            fonction pour trier les joueurs en fonction de leur score sur le jeu
        */
        Collections.sort(scores_list, (o1, o2) -> Double.compare(o1.getScore(), o2.getScore()));

        for(int i = 0; i < scores_list.size(); i++){
            scores_list.get(i).setPosition(i+1);
        }

        int last_player_position = 0;
        for (Score player : scores_list){
            if (player.getId() == last_player_id){
                last_player_position = player.getPosition();
                last_player_position--;
            }
        }
        int max_players_in_leader_board = 9;
        if(scores_list.size()<9){
            max_players_in_leader_board = scores_list.size();
        }
        for(int i = 0; i <= max_players_in_leader_board-1; i++) {
            if (scores_list.get(last_player_position).getScore() < scores_list.get(i).getScore()){
                lastPlayer_ScoreBoard = false;
            }
            ScoreBoardFragment fragment = new ScoreBoardFragment(scores_list.get(i).getUsername(), scores_list.get(i).getTime(), " "+(scores_list.get(i).getPosition()));
            getSupportFragmentManager().beginTransaction().add(R.id.ScoreBoard, fragment).commit();
        }
        if (lastPlayer_ScoreBoard && max_players_in_leader_board==9){
            ScoreBoardFragment fragment = new ScoreBoardFragment(scores_list.get(last_player_position).getUsername(), scores_list.get(last_player_position).getTime(), " "+(scores_list.get(last_player_position).getPosition()));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ScoreBoard, fragment)
                    .commit();
           
            lastPlayer_ScoreBoard = false;
        }

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