package com.ricky.model;

import java.util.Date;

public class Player extends User{
    private String question;
    private String answer;
    private String date;
    private String time;

    public Player(){}

    public Player(String userID, String password, String username,
                  String question, String answer) {
        super(userID, username, password, 1);
        this.question = question;
        this.answer = answer;
    }

    public Player(String userID, String password, String username,
                  String question, String answer, String date,
                  String time)
    {
        super(userID, username, password, 1);
        this.question = question;
        this.answer = answer;
        this.date = date;
        this.time = time;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
}
