package com.example.beermanager.Classes;

import com.google.firebase.database.Exclude;

import androidx.annotation.NonNull;

public class Player {
    //TODO how do we prevent the key from being modified while still allowing it to load into the Player class?
    @Exclude
    private String playerName;
    private String playerType;
    private String id = null;

    public Player(){}

    public Player(String id, String name, String type) {
        playerName = name;
        playerType = type;
        this.id = id;
    }



    public String getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    @NonNull
    @Override
    public String toString() {
        return this.playerName;
    }
}

