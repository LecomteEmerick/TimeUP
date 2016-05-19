package com.lechot.esgi.timeup;

/**
 * Created by Bastien on 18/05/2016.
 */
public class Player {
    private String name;
    public int RightAnswerScore=0;
    public int SkipAnswerScore=0;

    public Player(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
