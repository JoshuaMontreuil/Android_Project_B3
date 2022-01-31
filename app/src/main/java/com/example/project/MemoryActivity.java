package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MemoryActivity extends AppCompatActivity {

    protected Partie partie;
    protected ArrayList<CardFragment> fragmentCards = new ArrayList<>();
    protected MediaPlayer backgroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
    }

    @Override
    protected void onStart() {
        super.onStart();
        CardFragment card1 = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_fragment_1);
        CardFragment card2 = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_fragment_2);
        CardFragment card3 = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_fragment_3);
        CardFragment card4 = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_fragment_4);
        CardFragment card5 = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_fragment_5);
        CardFragment card6 = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.card_fragment_6);
        fragmentCards.add(card1);fragmentCards.add(card2);fragmentCards.add(card3);fragmentCards.add(card4);fragmentCards.add(card5);fragmentCards.add(card6);
        partie = new Partie(6,fragmentCards);
        backgroundMusic = MediaPlayer.create(this, R.raw.wii);
    }

    @Override
    protected void onResume() {
        super.onResume();
        partie.updateDisplay();
        backgroundMusic.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        backgroundMusic.pause();
    }

}