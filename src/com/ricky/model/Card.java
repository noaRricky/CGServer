package com.ricky.model;

import java.io.File;
import java.io.IOException;

public class Card {
    private int cardNo;
    private String cardName;
    private String photoPath;   //存放图片位置
    private int cardHP;
    private int cardAttack;
    private int cardType;

    public Card(){}

    public Card(int cardNo, String cardName, String photoPath, int cardHP, int cardAttack, int cardType) {
        this.cardNo = cardNo;
        this.cardName = cardName;
        this.photoPath = photoPath;
        this.cardHP = cardHP;
        this.cardAttack = cardAttack;
        this.cardType = cardType;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNumber) {
        this.cardNo = cardNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getCardHP() {
        return cardHP;
    }

    public void setCardHP(int cardHP) {
        this.cardHP = cardHP;
    }

    public int getCardAttack() {
        return cardAttack;
    }

    public void setCardAttack(int cardAttack) {
        this.cardAttack = cardAttack;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber=" + cardNo +
                ", cardName='" + cardName + '\'' +
                ", photoNumber='" + photoPath + '\'' +
                ", cardHP=" + cardHP +
                ", cardAttack=" + cardAttack +
                ", cardType=" + cardType +
                '}';
    }

    public static void main(String[] args) {
        File file = new File("");
        try {
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
