package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.model.Card;
import com.ricky.service.CardService;
import com.ricky.util.ImageHelper;
import com.ricky.util.ModelUri;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        String cardName = req.getParameter(ModelUri.CARD_NAME);
        String cardHP = req.getParameter(ModelUri.CARD_HP);
        String cardAttack = req.getParameter(ModelUri.CARD_ATTACK);
        String photoName = req.getParameter(ModelUri.CARD_PIC_NAME);
        String type = req.getParameter(ModelUri.CARD_TYPE);

        Card card = new Card(0, cardName, photoName,
                Integer.parseInt(cardHP),
                Integer.parseInt(cardAttack),
                Integer.parseInt(type));

        CardService service = new CardService();
        boolean result = service.addCard(card);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("result", result);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        resp.getWriter().println(jsonObj.toString());
    }
}
