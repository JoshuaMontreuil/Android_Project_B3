package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.TextView;
import java.util.ArrayList;

public class MemoryActivity extends AppCompatActivity {

    protected Partie partie;
    protected ArrayList<CardFragment> fragmentCards = new ArrayList<>();
    protected TextView username_memory;
    private SharedPreferences prefs;
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        username_memory = findViewById(R.id.activity_memory_username);
        prefs = getSharedPreferences("MY_PREFS_NAME",MODE_PRIVATE);
        chronometer = findViewById(R.id.simpleChronometer);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String current_username = prefs.getString("CURRENT_USERNAME","");
        username_memory.setText(current_username);
        CardFragment card1 = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_fragment_1);card1.setImgRes(R.drawable.start);
        CardFragment card2 = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_fragment_2);card2.setImgRes(R.drawable.start);
        CardFragment card3 = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_fragment_3);card3.setImgRes(R.drawable.start);
        CardFragment card4 = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_fragment_4);card4.setImgRes(R.drawable.start);
        CardFragment card5 = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_fragment_5);card5.setImgRes(R.drawable.start);
        CardFragment card6 = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_fragment_6);card6.setImgRes(R.drawable.start);
        fragmentCards.add(card1);fragmentCards.add(card2);fragmentCards.add(card3);fragmentCards.add(card4);fragmentCards.add(card5);fragmentCards.add(card6);
        partie = new Partie(6,fragmentCards);
        partie.setChronometer(chronometer);
        partie.getChronometer().start();
        for( CardFragment fragment : fragmentCards){
            fragment.setAssociatedPrefs(this.prefs);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        partie.updateDisplay();
    }

}