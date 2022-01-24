package com.example.project;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Partie{
    private int nbCartes;
    private ArrayList<ObjetCarte> cartes = new ArrayList<>();
    private int listChoisie;
    private ArrayList<String> faceCartes = new ArrayList<>();
    private ArrayList<String> dosCartes = new ArrayList<>();
    private int listePaires[] = {0,0,0,0,0,0};
    private ArrayList<String[]> listCartes;
    private String[] imagesClassiques ={"avion","bateau","chaussure","chien","dent","ecureuil","fraise","grenouille","herisson","igloo","journal","kangourou","lapin","maison"};

    public Partie(int nbCarte,int liste){
        this.nbCartes = nbCarte;
        this.listChoisie = liste;
        if(liste ==0){
            listCartes = new ArrayList<>();
            listCartes.add(imagesClassiques);
        }

    }

    public ArrayList<ObjetCarte> randomCartes(int nbCartes,ArrayList<ObjetCarte> cartes,int listChoisie){
        Random r = new Random();

        int random=0;
        ArrayList<Integer> randomValue = new ArrayList<Integer>();

        for (int i=0;i<nbCartes/2;i++) {

            boolean verif_e=false;

            while (verif_e==false) {
                random = r.nextInt(12);
                if(i!=0){
                    verif_e=verifSimilarValue(i,randomValue,random);}
                else{
                    verif_e=true;
                }
            }
            randomValue.add(i,random);
            int l=i*2;
            for(int j=0;j<2;j++){
                ObjetCarte carte=new ObjetCarte();
                carte.setImageDos("card");
                carte.setImageFace(listCartes.get(listChoisie)[random]);
                carte.setId(l);
                l++;
                cartes.add(i,carte);
            }
        }
        return cartes;
    }
    public boolean verifSimilarValue(int i,ArrayList<Integer> randomValue,int random){
        int similarValue=0;
        for (int k=0;k<i;k++){
            if (randomValue.get(k)==random){
                similarValue++;
            }
        }
        if(similarValue!=0){
            return false;
        }
        else{
            return true;
        }
    }

    public ArrayList<String> initFaceCartes(ArrayList<ObjetCarte> objet){
        for(int i = 0; i <nbCartes;i ++){
            this.faceCartes.add(objet.get(i%3).getImageFace());
        }
        for(int i = 0; i < 5; i++){
            Collections.shuffle(this.faceCartes);
        }
        for(int i = 1; i < nbCartes+1; i++){
            setListePaires(i);
        }

        return this.faceCartes;
    }

    public ArrayList<String> initDosCartes(ArrayList<ObjetCarte> objet){
        for(int i = 0; i <nbCartes;i ++){
            dosCartes.add(objet.get(i%3).getImageDos());
        }

        return dosCartes;
    }

    public void setListePaires(int index){
        String image = this.faceCartes.get(index-1);
        for(int i = 0; i < this.faceCartes.size(); i++){
            if(i != index-1){
                if(image.equals(this.faceCartes.get(i))){
                    this.listePaires[index - 1] = index;
                    this.listePaires[i] = index;
                }
            }
        }
    }

    public int getNbCartes() {
        return this.nbCartes;
    }

    public ArrayList<ObjetCarte> getCartes() {
        return this.cartes;
    }

    public int getListChoisie(){
        return this.listChoisie;
    }

    public int[] getListePaires() {
        return this.listePaires;
    }
}
