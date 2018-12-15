package com.example.joost.triva;

import java.io.Serializable;

public class PlayerScore implements Serializable {
    private String name;
    private int score;

    // constructor
    public PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
