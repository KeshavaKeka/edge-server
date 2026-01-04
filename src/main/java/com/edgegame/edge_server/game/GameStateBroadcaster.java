package com.edgegame.edge_server.game;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class GameStateBroadcaster {
    
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    public void addSession(WebSocketSession session){
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session){
        sessions.remove(session);
    }

    public void broadcast(GameState state){
        for (WebSocketSession session : sessions){
            try{
                session.sendMessage(
                    new TextMessage("PlayerX = " + state.getPlayerX())
                );
            } catch(IOException e){
                sessions.remove(session);
            }
        }
    }
}
