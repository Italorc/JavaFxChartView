package com.example.javaassgn1chartview;

public class WorldCup {
    private String edition;
    private double goalsAvg;

    public WorldCup(String edition, double goalsAvg) {
        this.edition = edition;
        this.goalsAvg = goalsAvg;
    }

    public WorldCup() {
       this("",0.0);
    }

    public WorldCup(String s, int i, int i1) {
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public double getGoalsAvg() {
        return goalsAvg;
    }

    public void setGoalsAvg(double goalsAvg) {
        this.goalsAvg = goalsAvg;
    }
}
