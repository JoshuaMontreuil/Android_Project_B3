package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ChronoFragment extends Fragment {

    private TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chrono, container, false);
        text = view.findViewById(R.id.textChrono);
        return view;
    }

    public void setText(int path){
        text.setText(path);
    }

}