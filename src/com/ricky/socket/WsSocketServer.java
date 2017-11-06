package com.ricky.socket;

import com.ricky.model.Message;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint("/game_socket")
public class WsSocketServer {
    private static String waitPlayer = null;
    private static Map<String, Session> sendMap = new HashMap<>();

    private String player;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Server connection...");
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Close connection..");
    }

    @OnMessage
    public String onMessage(Session session, String message) {
        System.out.println("Message from client: " + message);
        String echoMsg = "Echo from the server :" + message;
        return echoMsg;
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }
}
