package com.example.project;

import java.io.Serializable;
import java.util.List;

public class Score {

    private String username;
    private String time;

    Score (String username, String time){
        this.username = username;
        this.time = time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }
}
