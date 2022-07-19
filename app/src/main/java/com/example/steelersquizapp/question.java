package com.example.steelersquizapp;

public class question {

    String qText;
    boolean corAns;

    public question(String qText, boolean corAns) {
        this.qText = qText;
        this.corAns = corAns;
    }

    public String getQText() {
        return qText;
    }

    public void setQText(String qText) {
        this.qText = qText;
    }

    public boolean getCorAns() {
        return corAns;
    }

    public void setCorAns(boolean corAns) {
        this.corAns = corAns;
    }

    @Override
    public String toString() {
        return "question{" +
                "qText='" + qText + '\'' +
                ", corAns=" + corAns +
                '}';
    }
}
