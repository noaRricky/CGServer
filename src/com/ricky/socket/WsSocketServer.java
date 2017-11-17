package com.ricky.socket;

import com.ricky.model.Message;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.String;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint("/game_socket")
public class WsSocketServer {
    private static String waitPlayer = "";
    private static Session waitSession = null;
    private static boolean flag = false;
    private static Map<String, Session> sendMap = new HashMap<>();

    private String player;
    private Session playSession;
    private int order;

    private static final int FIRST_ORDER = 0;
    private static final int SECOND_ORDER = 1;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Server connection...");
    }

    @OnClose
    public void onClose(Session session) {

        System.out.println("Close connection..");

        for (String key : sendMap.keySet()) {
            if (sendMap.get(key) == session) {
                sendMap.remove(key);
            }
        }
    }

    @OnMessage
    public String onMessage(Session session, String message) {

        Message playMsg = new Message(message);
        this.playSession = session;
        if (playMsg.getType() == Message.Type.START) {
            player = playMsg.getPlayer();

            if (waitPlayer.equals("")) {
                synchronized (waitPlayer) {
                    waitPlayer = player;
                    waitSession = session;
                    order = FIRST_ORDER;
                }
            } else if (!waitPlayer.equals(player)) {
                synchronized (waitPlayer) {
                    sendMap.put(waitPlayer, session);
                    sendMap.put(player, waitSession);
                    order = SECOND_ORDER;
                    waitSession = null;
                    waitPlayer = "";
                }

            }

            while (sendMap.get(player) == null) {
                System.out.println("Wait for player......");
            }

            if (order == FIRST_ORDER) {
                Message retMessage = new Message(Message.Type.SECOND, player, playMsg.getDeck());
                Session battleSession = sendMap.get(player);
                sendMessage(battleSession, retMessage.toJSON());
            } else if (order == SECOND_ORDER) {
                Message retMessage = new Message(Message.Type.FIRST, player, playMsg.getDeck());
                Session battleSession = sendMap.get(player);
                sendMessage(battleSession, retMessage.toJSON());
            }
            //已经在进行游戏
        } else {
            Session battleSession = sendMap.get(player);
            sendMessage(battleSession, message);
            return null;
        }

        return null;
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    /**
     * 相对战者发送信息
     * @param session 对站者Session
     * @param message 信息
     */
    private void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
