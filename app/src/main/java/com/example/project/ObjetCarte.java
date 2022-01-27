package com.example.project;

public class ObjetCarte {
    private int id;
    private String imageFace;
    private String imageDos;
    private int state;
    private boolean found;

    ObjetCarte(int id, String imageFace, String imageDos){
        this.id = id;
        this.imageFace = imageFace;
        this.imageDos = imageDos;
        this.state = 0; //Face cachée
        this.found = false;
    }

    public boolean faceCardSet() {
        if (this.imageFace != "init") {
            return true;
        } else {
            return false;
        }
    }

    public void toogleState(){
        if(!found){
            if(this.state==0){
                this.state=1;
            }
            else{
                this.state=0;
            }
        }
    }

    public void setState(int state){
        this.state = state;
    }

    public void setFound(boolean value){
        this.found = value;
    }

    public boolean getFound(){ return found; }

    public int getState(){ return state; }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageFace(String imageFace) {
        this.imageFace = imageFace;
    }

    public String getImageFace() {
        return imageFace;
    }

    public void setImageDos(String imageDos) {
        this.imageDos = imageDos;
    }

    public String getImageDos() {
        return imageDos;
    }
}
