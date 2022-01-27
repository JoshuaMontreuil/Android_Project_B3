package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class MemoryActivity extends AppCompatActivity implements View.OnClickListener{

    protected ImageView carte1,carte2,carte3,carte4,carte5,carte6;
    protected ArrayList<ImageView> cartes;
    protected ArrayList<String> cartesFace,cartesDos;
    protected ArrayList<ObjetCarte> objetcartes = new ArrayList<>();
    protected Partie partie;
    protected boolean liste[];
    protected int listePaires[];
    protected int limiteClick = 0;
    protected int pairesTrouvés = 0;
    protected int pastID;
    protected boolean init = true , printImage = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        cartes = new ArrayList<>();
        partie = new Partie(6,0);
        carte1 = findViewById(R.id.carte1);
        carte2 = findViewById(R.id.carte2);
        carte3 = findViewById(R.id.carte3);
        carte4 = findViewById(R.id.carte4);
        carte5 = findViewById(R.id.carte5);
        carte6 = findViewById(R.id.carte6);
        liste = new boolean[]{false, false, false, false,false,false};
        cartes.add(carte1);cartes.add(carte2);cartes.add(carte3);cartes.add(carte4);cartes.add(carte5);cartes.add(carte6);
        objetcartes = partie.randomCartes(partie.getNbCartes(),partie.getCartes(),partie.getListChoisie());
        cartesFace = partie.initFaceCartes(objetcartes);cartesDos = partie.initDosCartes(objetcartes);
        listePaires = partie.getListePaires();
    }

    @Override
    protected void onResume() {
        super.onResume();


        pushCardsToBoard(0,0,0,0,false); // on initialise les cartes de dos.
        for(int i =0; i < cartes.size(); i++) {
            cartes.get(i).setOnClickListener(this); //on active un event listener pour chaque cartes du plateau
        }
    }

    @Override
    public void onClick(View view){
        int ID = view.getId();
        boolean getIteration = false;
        for(int i=0;i<cartes.size();i++) {
            if (ID == cartes.get(i).getId()) {
                getIteration = liste[i];
            }
        }
        if(limiteClick == 2){
            for(int i = 0; i < cartes.size(); i++){
                if(liste[i] == true){
                    liste[i] = false;
                }
            }
            limiteClick = 0;
            init = true;
            pushCardsToBoard(0,0,0,0,false);
        }
        for(int i=0; i< cartes.size();i++){
            if (ID == cartes.get(i).getId()){
                if(!getIteration) {
                    limiteClick++;
                    if(limiteClick == 2){
                        pushCardsToBoard(i,1,ID,pastID,true);
                    }else{
                        pushCardsToBoard(i,1,0,0,false);
                    }
                    liste[i] = true;
                }
            }
        }
        pastID = ID;
    }


    protected void pushCardsToBoard(int index, int side,int ID, int pastID, boolean twoCards){
        if(init){
            for(int i = 0; i < cartes.size(); i++){
                cartes.get(i).setImageResource(getResources().getIdentifier(cartesDos.get(i),"drawable",getPackageName()));
            }
            init = false;
        }
        if(side == 1) {
            cartes.get(index).setImageResource(getResources().getIdentifier(cartesFace.get(index), "drawable", getPackageName()));
            if(twoCards){
                isEven(ID,pastID);
            }
        }
    }

    protected void isEven(int index, int pastIndex){
        for(int i = 0; i < cartes.size();i++){
            for(int j =0; j<cartes.size();j++){
                if((index == cartes.get(i).getId()) && (pastIndex == cartes.get(j).getId())){
                    if(listePaires[i] == listePaires[j]){
                        pairesTrouvés ++;
                        cartes.get(i).setVisibility(View.INVISIBLE);
                        cartes.get(j).setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
    }


}