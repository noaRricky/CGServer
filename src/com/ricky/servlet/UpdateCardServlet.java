package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.model.Card;
import com.ricky.service.CardService;
import com.ricky.util.ModelUri;
import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        String cardID = req.getParameter(ModelUri.CARD_ID);
        String cardName = req.getParameter(ModelUri.CARD_NAME);
        String cardPhotoName = req.getParameter(ModelUri.CARD_PIC_NAME);
        String cardAttack = req.getParameter(ModelUri.CARD_ATTACK);
        String cardHP = req.getParameter(ModelUri.CARD_HP);
        String cardType = req.getParameter(ModelUri.CARD_TYPE);

        Card card = new Card(
                Integer.parseInt(cardID),
                cardName, cardPhotoName,
                Integer.parseInt(cardHP),
                Integer.parseInt(cardAttack),
                Integer.parseInt(cardType)
        );

        CardService service = new CardService();
        boolean ret = service.updateCard(card);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ModelUri.RESULT, ret);

        resp.getWriter().println(jsonObject.toString());
    }
}
