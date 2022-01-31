package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CardFragment extends Fragment implements View.OnClickListener{

    private int cardId;
    private int img_ref;
    private ImageView imgViewFromFragment;
    private ObjetCarte associatedCard;
    private Partie associatedGameBoard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        imgViewFromFragment = view.findViewById(R.id.card);
        imgViewFromFragment.setImageResource(this.img_ref);
        imgViewFromFragment.setOnClickListener(this);
        return view;
    }

    public void updateCardDisplay(){
        if(associatedCard.getState()==0){
            this.updateToBackImg();
        }
        else{
            this.updateToFaceImg();
        }
    }

    public void setImgRes(int img_ref){
        this.img_ref = img_ref;
        ImageView card_img = getView().findViewById(R.id.card);
        card_img.setImageResource(this.img_ref);
    }

    public void updateToFaceImg(){
        if(associatedCard.getImageFace()=="avion"){
            this.setImgRes(R.drawable.avion);
        }
        else if(associatedCard.getImageFace()=="bateau"){
            this.setImgRes(R.drawable.bateau);
        }
        else if(associatedCard.getImageFace()=="bonhomme1"){
            this.setImgRes(R.drawable.bonhomme1);
        }
        else if(associatedCard.getImageFace()=="card"){
            this.setImgRes(R.drawable.card);
        }
        else if(associatedCard.getImageFace()=="chaussure"){
            this.setImgRes(R.drawable.chaussure);
        }
        else if(associatedCard.getImageFace()=="chien"){
            this.setImgRes(R.drawable.chien);
        }
        else if(associatedCard.getImageFace()=="dent"){
            this.setImgRes(R.drawable.dent);
        }
        else if(associatedCard.getImageFace()=="ecureuil"){
            this.setImgRes(R.drawable.ecureuil);
        }
        else if(associatedCard.getImageFace()=="fraise"){
            this.setImgRes(R.drawable.fraise);
        }
        else if(associatedCard.getImageFace()=="grenouille"){
            this.setImgRes(R.drawable.grenouille);
        }
        else if(associatedCard.getImageFace()=="herisson"){
            this.setImgRes(R.drawable.herisson);
        }
        else if(associatedCard.getImageFace()=="igloo"){
            this.setImgRes(R.drawable.igloo);
        }
        else if(associatedCard.getImageFace()=="journal"){
            this.setImgRes(R.drawable.journal);
        }
        else if(associatedCard.getImageFace()=="kangourou"){
            this.setImgRes(R.drawable.kangourou);
        }
        else if(associatedCard.getImageFace()=="lapin"){
            this.setImgRes(R.drawable.lapin);
        }
        else if(associatedCard.getImageFace()=="maison"){
            this.setImgRes(R.drawable.maison);
        }
        else{
            System.out.println("Failed to set Face img : "+associatedCard.getImageFace());
            this.setImgRes(R.drawable.start);
        }
    }

    public void updateToBackImg(){
        this.setImgRes(R.drawable.card);
    }

    public void setAssociatedCard(ObjetCarte associated_card) {
        this.associatedCard = associated_card;
        this.cardId = associated_card.getId();
    }

    public void setAssociatedGameBoard(Partie game){
        this.associatedGameBoard = game;
    }

    @Override
    public void onClick(View v) {
        if(!associatedGameBoard.revealAllowed()){ associatedGameBoard.hideAllCards(); }
        associatedCard.toogleState();
        this.updateCardDisplay();
        String pairName = associatedGameBoard.pairFound();
        //Manage board
        if(!pairName.equals("")){ //On cache et bloque les cartes
            ArrayList<ObjetCarte> cards = associatedGameBoard.getCards();
            ArrayList<CardFragment> cardFragments = associatedGameBoard.getCardFragments();
            for(int i=0;i<cards.size();i++){
                if(cards.get(i).getImageFace().equals(pairName)){
                    for(int x=0;x<cardFragments.size();x++){
                        cardFragments.get(i).getImgViewFromFragment().setVisibility(View.INVISIBLE);
                        cards.get(i).setFound(true);
                    }
                }
            }
        }
        //Check for end of game
        if(associatedGameBoard.allFound()){
            //Go to the next activity
            Intent intent = new Intent(getContext(),EndActivity.class);
            startActivity(intent);
            System.out.println("You won");
        }
    }

    public ImageView getImgViewFromFragment(){
        return this.imgViewFromFragment;
    }

    public int getCardId(){
        return this.cardId;
    }

    public ObjetCarte getAssociatedCard(){
        return this.associatedCard;
    }
}