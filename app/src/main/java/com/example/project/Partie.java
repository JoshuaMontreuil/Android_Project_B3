package com.example.project;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Partie{
    private final int cardsNb;
    private ArrayList<ObjetCarte> cards = new ArrayList<>();
    private ArrayList<CardFragment> cardFragments = new ArrayList<>();
    private List<String> choosenImgs = new ArrayList<>();
    private List<String> cards_imgs = new ArrayList<>(Arrays.asList("avion","bateau","chaussure","chien","dent","ecureuil","fraise","grenouille","herisson","igloo","journal","kangourou","lapin","maison"));

    public Partie(int cardsNb, ArrayList<CardFragment> fragments){
        this.cardsNb = cardsNb;
        initGame(fragments);
    }

    public void initGame(ArrayList<CardFragment> fragments){
        this.associateCardsToFragments(fragments);
        this.bindRandomPairsToCards();
        this.updateDisplay();
    }

    public void associateCardsToFragments(ArrayList<CardFragment> fragments){
        for(int i=0;i<fragments.size();i++){
            ObjetCarte new_card = new ObjetCarte(i,"init","init");
            fragments.get(i).setAssociatedCard(new_card);
            fragments.get(i).setAssociatedGameBoard(this);
            cards.add(new_card);
            cardFragments.add(fragments.get(i));
        }
    }

    public void bindRandomPairsToCards(){
        Random rand = new Random();
        choosenImgs = this.selectPairs();
        for(int x=0;x<choosenImgs.size()-x;x++){
            choosenImgs.add(choosenImgs.get(x));
        }
        for(int i=0;i<cards.size();i++){ //Image de face
            int index = rand.nextInt(choosenImgs.size());
            cards.get(i).setImageFace(choosenImgs.get(index));
            choosenImgs.remove(index);
        }
        for(int i=0;i<cards.size();i++){ //Image de dos
            cards.get(i).setImageDos("card");
        }
    }

    public ArrayList<String> selectPairs(){
        Random rand = new Random();
        List<String> choosenPairs = new ArrayList<>();
        //On choisit des cartes dans la liste des cartes puis on les ajoute dans choosenPairs
        for(int i=0;i<this.getCardsNb()/2;i++){
            String randomElement = cards_imgs.get(rand.nextInt(cards_imgs.size()));
            choosenPairs.add(randomElement);
            cards_imgs.remove(randomElement); //On ne veut pas 2 fois la même paire
        }
        return (ArrayList<String>) choosenPairs;
    }

    public void hideAllCards(){
        for(int i=0;i<cardFragments.size();i++){
            cardFragments.get(i).getAssociatedCard().setState(0);
            this.updateDisplay();
        }
    }

    public void revealAllCards(){
        for(int i=0;i<cardFragments.size();i++){
            cardFragments.get(i).getAssociatedCard().setState(1);
            this.updateDisplay();
        }
    }

    public boolean revealAllowed(){
        int revealedCards = 0;
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).getState()==1){
                revealedCards++;
            }
        }
        if(revealedCards==2){
            return false;
        }
        else{
            return true;
        }
    }

    public String pairFound(){
        ArrayList<ObjetCarte> revealedCards = new ArrayList<>();
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).getState()==1){
                revealedCards.add(cards.get(i));
            }
        }
        if(revealedCards.size()==2){
            if(revealedCards.get(0).getImageFace().equals(revealedCards.get(1).getImageFace())){
                //A pair has been found
                return revealedCards.get(0).getImageFace();
            }
            else{
                return "";
            }
        }
        else{
            return "";
        }
    }

    public boolean allFound(){
        int foundCards = 0;
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).getFound()){
                foundCards++;
            }
        }
        if(foundCards==cardsNb){
            return true;
        }
        else{
            return false;
        }
    }

    public void updateDisplay(){
        for(int i=0;i<cards.size();i++){
            cardFragments.get(i).updateCardDisplay();
        }
    }

    public int getCardsNb() {
        return this.cardsNb;
    }

    public ArrayList<ObjetCarte> getCards() {
        return this.cards;
    }

    public ArrayList<CardFragment> getCardFragments() {
        return this.cardFragments;
    }
}
