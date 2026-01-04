package com.edgegame.edge_server;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.edgegame.edge_server.game.GameLoop;
import com.edgegame.edge_server.game.GameStateBroadcaster;

@Component
public class GameWebSocketHandler extends TextWebSocketHandler{

    private final GameStateBroadcaster broadcaster;
    private final GameLoop gameLoop;

    public GameWebSocketHandler(GameStateBroadcaster broadcaster, GameLoop gameLoop){
        this.gameLoop = gameLoop;
        this.broadcaster = broadcaster;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException{
        
        broadcaster.addSession(session);

        session.sendMessage(
            new TextMessage(
                "Connected PlayerX = " + gameLoop.getGameState().getPlayerX()
            )
        );
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        broadcaster.removeSession(session);
    }
}