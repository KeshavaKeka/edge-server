package com.edgegame.edge_server;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.edgegame.edge_server.game.GameLoop;

@Component
public class GameWebSocketHandler extends TextWebSocketHandler{

    private final GameLoop gameLoop;

    public GameWebSocketHandler(GameLoop gameLoop){
        this.gameLoop = gameLoop;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException{
        session.sendMessage(
            new TextMessage(
                "Connected PlayerX = " + gameLoop.getGameState().getPlayerX()
            )
        );
    }
}