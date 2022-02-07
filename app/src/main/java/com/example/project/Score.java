package com.example.project;

public class Score {

    private String username;
    private String time;
    private int id;
    private int score;
    private int position;

    Score (String username, String time, int score, int id, int position){
        this.username = username;
        this.time = time;
        this.score = score;
        this.id = id;
        this.position = position;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
