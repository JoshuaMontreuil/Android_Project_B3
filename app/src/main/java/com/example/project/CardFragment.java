package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

public class CardFragment extends Fragment implements View.OnClickListener{

    private int cardId;
    private int img_ref;
    private ImageView imgViewFromFragment;
    private ObjetCarte associatedCard;
    private Partie associatedGameBoard;
    private SharedPreferences associatedPrefs;
    private MediaPlayer victorySound;
    private MediaPlayer revealSound;
    private MediaPlayer allRevealedSound;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        imgViewFromFragment = view.findViewById(R.id.card);
        imgViewFromFragment.setImageResource(this.img_ref);
        imgViewFromFragment.setOnClickListener(this);
        victorySound = MediaPlayer.create(getContext(), R.raw.cartoon);
        revealSound = MediaPlayer.create(getContext(), R.raw.reveal);
        allRevealedSound = MediaPlayer.create(getContext(), R.raw.victory);
        return view;
    }

    public void setAssociatedPrefs(SharedPreferences prefs){
        this.associatedPrefs = prefs;
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
        if(associatedCard.getImageFace().equals("avion")){
            this.setImgRes(R.drawable.avion);
        }
        else if(associatedCard.getImageFace().equals("bateau")){
            this.setImgRes(R.drawable.bateau);
        }
        else if(associatedCard.getImageFace().equals("bonhomme1")){
            this.setImgRes(R.drawable.bonhomme1);
        }
        else if(associatedCard.getImageFace().equals("card")){
            this.setImgRes(R.drawable.card);
        }
        else if(associatedCard.getImageFace().equals("chaussure")){
            this.setImgRes(R.drawable.chaussure);
        }
        else if(associatedCard.getImageFace().equals("chien")){
            this.setImgRes(R.drawable.chien);
        }
        else if(associatedCard.getImageFace().equals("dent")){
            this.setImgRes(R.drawable.dent);
        }
        else if(associatedCard.getImageFace().equals("ecureuil")){
            this.setImgRes(R.drawable.ecureuil);
        }
        else if(associatedCard.getImageFace().equals("fraise")){
            this.setImgRes(R.drawable.fraise);
        }
        else if(associatedCard.getImageFace().equals("grenouille")){
            this.setImgRes(R.drawable.grenouille);
        }
        else if(associatedCard.getImageFace().equals("herisson")){
            this.setImgRes(R.drawable.herisson);
        }
        else if(associatedCard.getImageFace().equals("igloo")){
            this.setImgRes(R.drawable.igloo);
        }
        else if(associatedCard.getImageFace().equals("journal")){
            this.setImgRes(R.drawable.journal);
        }
        else if(associatedCard.getImageFace().equals("kangourou")){
            this.setImgRes(R.drawable.kangourou);
        }
        else if(associatedCard.getImageFace().equals("lapin")){
            this.setImgRes(R.drawable.lapin);
        }
        else if(associatedCard.getImageFace().equals("maison")){
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
            revealSound.start();
            ArrayList<ObjetCarte> cards = associatedGameBoard.getCards();
            ArrayList<CardFragment> cardFragments = associatedGameBoard.getCardFragments();
            for(int i=0;i<cards.size();i++){
                if(cards.get(i).getImageFace().equals(pairName)){
                    for(int x=0;x<cardFragments.size();x++){
                        //cardFragments.get(i).getImgViewFromFragment().setVisibility(View.INVISIBLE);
                        cardFragments.get(i).getImgViewFromFragment().setOnClickListener(null);
                        cards.get(i).setFound(true);
                    }
                }
            }
        }
        //Check for end of game
        if(associatedGameBoard.allFound()){
            //Save data to the application
            associatedGameBoard.getChronometer().stop();
            String current_username = associatedPrefs.getString("CURRENT_USERNAME","");
            SharedPreferences.Editor editor = associatedPrefs.edit();
            String time = (String) associatedGameBoard.getChronometer().getText();
            //Get previous scores
            ArrayList<Score> scores_list = new ArrayList<>();
            Gson gson = new Gson();
            String json_scores = associatedPrefs.getString("SCORES","");
            scores_list = gson.fromJson(json_scores,new TypeToken<ArrayList<Score>>(){}.getType());
            Score score = new Score(current_username, time, 0, 0, 0);
            if(scores_list==null){
                scores_list = new ArrayList<>();
            }
            scores_list.add(score);
            String json_score = gson.toJson(scores_list);
            editor.putString("SCORES", json_score);
            editor.apply();
            //Go to the next activity
            allRevealedSound.start();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                public void run(){
                    victorySound.start();
                    Intent intent = new Intent(getContext(),EndActivity.class);
                    startActivity(intent);
                }
            },1500);
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