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
    private static List<Integer> waitDeck = null;

    private String player;
    private String match;
    private List<Integer> deck;
    private List<Integer> battleDeck;
    private Session playSession;

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

        System.out.println("Game Socket Server: receive message: " + message);

        Message playMsg = new Message(message);
        this.playSession = session;
        player = playMsg.getPlayer();
        String str = null;


        if (playMsg.getType() == Message.Type.START) {
            deck = playMsg.getDeck();
            if (waitPlayer.equals("")) {
                synchronized (waitPlayer) {
                    waitPlayer = player;
                    waitSession = session;
                    waitDeck = this.deck;
                }

                Message ret = new Message(Message.Type.WAIT, player);
                str = ret.toJSON();
            } else if (!waitPlayer.equals(player)) {
                synchronized (waitPlayer) {
                    sendMap.put(waitPlayer, session);
                    sendMap.put(player, waitSession);
                    match = waitPlayer;
                    battleDeck = waitDeck;
                    waitSession = null;
                    waitPlayer = "";
                    waitDeck = null;
                }

                Message firstMessage = new Message(Message.Type.FIRST, player, deck);
                Session battleSession = sendMap.get(player);
                sendMessage(battleSession, firstMessage.toJSON());

                Message second = new Message(Message.Type.SECOND, match, battleDeck);
                str = second.toJSON();
            }
            //表示战斗结束
        } else if (playMsg.getType() == Message.Type.END) {

            Session battleSession = sendMap.get(player);
            if (battleSession != null) {
                sendMessage(battleSession, message);
            }
            sendMap.remove(player);
        }
        //表示已经开始战斗
        else {

            Session battleSession = sendMap.get(player);

            sendMessage(battleSession, message);

            Message ret = new Message(Message.Type.WAIT, player);
            str = ret.toJSON();
        }

        return str;
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

            for (String key : sendMap.keySet()) {
                if (sendMap.get(key) == session) {
                    sendMap.remove(key);
                }
            }
        }
    }
}
