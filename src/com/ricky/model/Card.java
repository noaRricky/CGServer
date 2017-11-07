package com.ricky.model;

import java.io.File;
import java.io.IOException;

public class Card {

    private int cardID;
    private String cardName;
    private String cardPhotoName;
    private int cardHP;
    private int cardAttack;
    private int cardType;

    public Card(){}

    /**
     * 创建卡牌
     * @param cardID 卡牌ID
     * @param cardName 卡牌名字
     * @param cardPhotoName 卡牌图片名字
     * @param cardHP 卡牌HP
     * @param cardAttack 卡牌攻击力
     * @param cardType 卡牌类型
     */
    public Card(int cardID, String cardName, String cardPhotoName, int cardHP, int cardAttack, int cardType) {
        this.cardID = cardID;
        this.cardName = cardName;
        this.cardPhotoName = cardPhotoName;
        this.cardHP = cardHP;
        this.cardAttack = cardAttack;
        this.cardType = cardType;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardPhotoName() {
        return cardPhotoName;
    }

    public void setCardPhotoName(String cardPhotoName) {
        this.cardPhotoName = cardPhotoName;
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
}
