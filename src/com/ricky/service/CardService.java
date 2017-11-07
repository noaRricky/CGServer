package com.ricky.service;

import com.org.json.JSONArray;
import com.org.json.JSONObject;
import com.ricky.model.Card;
import com.ricky.util.ModelUri;
import com.ricky.util.SQLHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardService {

    private SQLHelper helper = null;   //底层实务处理
    private ResultSet rs = null;
    private Card card = null;

    /**
     * 根据卡片的名字搜索卡牌信息
     * @param card_name 卡片名字
     * @return 如果该卡牌存在返回所有信息，否则返回null
     */
    public Card searchCard(String card_name)
    {
        helper = new SQLHelper();
        String sql = "SELECT * FROM card WHERE CardName=?";
        Object parameter[] = {card_name};

        try {
            rs = helper.query(sql, parameter);

            if (rs != null)
            {
                while (rs.next())
                {
                    card = new Card();

                    card.setCardID(rs.getInt(1));
                    card.setCardName(rs.getNString(2));
                    card.setCardPhotoName(rs.getString(3));
                    card.setCardHP(rs.getInt(4));
                    card.setCardAttack(rs.getInt(5));
                    card.setCardType(rs.getInt(6));
                }
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally {
            if (rs != null)
            {
                helper.close();
                helper = null;
            }
        }

        return card;
    }

    /**
     * 向卡牌信息中添加新的卡牌
     * @param newCard 新的卡牌信息
     * @return 如果添加成功返回true,否则返回false
     */
    public boolean addCard(Card newCard)
    {
        helper = new SQLHelper();

        boolean flag;
        String sql = "INSERT INTO card(CardName, CardPhotoName, CardHP, CardAttack, CardType)" +
                " VALUES (?, ?, ?, ?, ?) ";
        Object parameter[] = {newCard.getCardName(),
                            newCard.getCardPhotoName(), newCard.getCardHP(),
                            newCard.getCardAttack(), newCard.getCardType()};

        flag = helper.execute(sql, parameter);
        helper = null;

        return flag;
    }

    /**
     *根据卡牌编号删除卡牌信息
     * @param card_num 卡牌编号
     * @return 如果删除成功返回true, 否则返回false
     */
    public boolean deleteCard(int card_num)
    {
        helper = new SQLHelper();

        boolean flag;
        String sql = "DELETE FROM card WHERE CardID=?";
        Object parameter[] = {card_num};

        flag = helper.execute(sql, parameter);
        helper = null;

        return flag;
    }

    /**
     * 改变卡牌信息
     * @param card 新的卡牌信息
     * @return 改变成功返回true, 否则返回false
     */
    public boolean updateCard(Card card)
    {
        helper = new SQLHelper();

        boolean flag;
        String sql = "UPDATE card SET CardName = ?, CardPhotoName = ?, " +
                "CardHP = ?, CardAttack = ?, CardType = ? WHERE CardID = ?";
        Object parameter[] = {card.getCardName(), card.getCardPhotoName(),
                            card.getCardHP(), card.getCardAttack(),
                            card.getCardType(), card.getCardID()};

        flag = helper.execute(sql, parameter);
        helper = null;

        return flag;
    }

    public JSONArray getAllCard() {
        helper = new SQLHelper();
        JSONArray jsonArray = null;

        String sql = "SELECT * FROM card";

        try {
            rs = helper.query(sql, null);

            if (rs != null) {
                jsonArray = new JSONArray();
                while (rs.next()) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(ModelUri.CARD_ID, rs.getInt(1));
                    jsonObject.put(ModelUri.CARD_NAME, rs.getNString(2));
                    jsonObject.put(ModelUri.CARD_PIC_NAME, rs.getString(3));
                    jsonObject.put(ModelUri.CARD_HP, rs.getInt(4));
                    jsonObject.put(ModelUri.CARD_ATTACK, rs.getInt(5));
                    jsonObject.put(ModelUri.CARD_TYPE, rs.getInt(6));

                    jsonArray.put(jsonObject);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public static void main(String args[])
    {
        CardService cardService = new CardService();

        Card card = new Card(1, "hikari", "1", 100, 50, 0);
        cardService.updateCard(card);
    }
}
