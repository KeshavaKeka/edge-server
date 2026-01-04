package com.edgegame.edge_server.game;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GameLoop{
    private final GameState gameState = new GameState();
    private final GameStateBroadcaster broadcaster;

    public GameLoop(GameStateBroadcaster broadcaster){
        this.broadcaster = broadcaster;
    }

    @Scheduled(fixedRate = 1000)
    public void tick() {
        gameState.increment();
        broadcaster.broadcast(gameState);
        System.out.println("Player X = " + gameState.getPlayerX());
    }

    public GameState getGameState(){
        return gameState;
    }
}