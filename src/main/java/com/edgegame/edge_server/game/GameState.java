package com.edgegame.edge_server.game;

public class GameState {
    private int playerX;

    public int getPlayerX(){
        return playerX;
    }

    public void increment(){
        playerX++;
    }
}
