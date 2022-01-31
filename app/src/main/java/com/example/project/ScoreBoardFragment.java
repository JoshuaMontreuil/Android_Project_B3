package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScoreBoardFragment extends Fragment {
    private final String username;
    private final String highScore;
    private final String position;


    ScoreBoardFragment(String usr,String hs, String pos){
        this.username = usr;
        this.highScore = hs;
        this.position = pos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_score_board, container, false);
        TextView positionText = view.findViewById(R.id.place);
        TextView usernameText = view.findViewById(R.id.username);
        TextView highScoreText = view.findViewById(R.id.highscore);
        positionText.setText(this.position);
        usernameText.setText(this.username);
        highScoreText.setText(this.highScore);
        return view;
    }
}
