package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.model.Card;
import com.ricky.service.CardService;
import com.ricky.util.ModelUri;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Card card = new Card(
                Integer.parseInt(req.getParameter(ModelUri.CARD_ID)),
                req.getParameter(ModelUri.CARD_NAME),
                req.getParameter(ModelUri.CARD_PIC_NAME),
                Integer.parseInt(req.getParameter(ModelUri.CARD_HP)),
                Integer.parseInt(req.getParameter(ModelUri.CARD_ATTACK)),
                Integer.parseInt(req.getParameter(ModelUri.CARD_TYPE))
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
