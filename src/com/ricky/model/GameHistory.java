package com.ricky.model;

import java.util.Date;

public class GameHistory {
    private int number;     //对战历史编号
    private String playerA;  //玩家A
    private String playerB;  //玩家B
    private String winner;  //赢家
    private String date ; //日期
    private String time ; //时间

    public GameHistory(){};

    public GameHistory(int number, String playerA, String playerB, String winner, String date, String time) {
        this.number = number;
        this.playerA = playerA;
        this.playerB = playerB;
        this.winner = winner;
        this.date = date;
        this.time = time;
    }

    public String getPlayerA() {
        return playerA;
    }

    public void setPlayerA(String playerA) {
        this.playerA = playerA;
    }

    public String getPlayerB() {
        return playerB;
    }

    public void setPlayerB(String playerB) {
        this.playerB = playerB;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDateTime() {
        return this.date + " " + this.time;
    }
}
