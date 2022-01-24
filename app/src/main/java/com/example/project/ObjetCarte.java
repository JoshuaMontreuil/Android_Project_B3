package com.example.project;

public class ObjetCarte {
    private int id;
    private String image;
    private String imageFace;
    private String imageDos;
    private int state=0;


    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
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
