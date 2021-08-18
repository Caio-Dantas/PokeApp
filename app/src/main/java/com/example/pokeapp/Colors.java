package com.example.pokeapp;

import android.graphics.Color;

public enum Colors {

    BLUE("#0000FF"),
    NORMAL("#e6c5ac"),
    FIRE("#f46a67"),
    WATER("#84c0d9"),
    GRASS("#4fccaf"),
    ELECTRIC("#ebd38c"),
    ICE("#a4c5de"),
    FIGHTING("#83414a"),
    POISON("#7b316a"),
    GROUND("#f6d5b4"),
    FLYING("#94c5ff"),
    PSYCHIC("#cd8bbd"),
    BUG("#acf641"),
    ROCK("#bdb4b4"),
    GHOST("#8b6283"),
    DARK("#838383"),
    DRAGON("#7b7bcd"),
    STEEL("#b4b4b4"),
    FAIRY("#ffacac"),
    ;

    private final String color;

    Colors(String color){
        this.color = color;
    }

    public int getColor(){
        return Color.parseColor(color);
    }

}
