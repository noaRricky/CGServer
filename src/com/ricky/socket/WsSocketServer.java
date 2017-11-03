package com.ricky.socket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/game_socket")
public class WsSocketServer {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Open Connection..");
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
