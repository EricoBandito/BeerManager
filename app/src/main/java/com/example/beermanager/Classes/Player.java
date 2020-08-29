package com.example.beermanager.Classes;

import com.google.firebase.database.Exclude;

public class Player {
    //TODO how do we prevent the key from being modified while still allowing it to load into the Player class?
    @Exclude
    public String key;
    public String playerName;
    public String playerType;

    public Player(){}

    public Player(String name, String type) {
        playerName = name;
        playerType = type;
    }

    @Override
    public String toString(){
        return playerName;
    }
}

