package com.example.steelersquizapp;

import java.util.ArrayList;

public class ScoreObject implements Comparable{
    private String name;
    private int score;

    public ScoreObject(String n, int s) {
        name = n;
        score = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String na) {
        name = na;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int sc) {
        score = sc;
    }

    public int compareTo(Object compareVal){
        int compareScore = ((ScoreObject) compareVal).getScore();
        return score - compareScore;
    }



    public String toString(){return name + ":: " + score;}


}

