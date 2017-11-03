package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.model.Card;
import com.ricky.service.CardService;
import com.ricky.util.ImageHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCardServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        String cardName = req.getParameter("cardName");
        String cardHP = req.getParameter("cardHP");
        String cardAttack = req.getParameter("cardAttack");
        String photoPath = req.getParameter("photoPath");
        String type = req.getParameter("cardType");

        Card card = new Card();
        card.setCardName(cardName);
        card.setPhotoPath(ImageHelper.IMAGE_BASE_PATH + photoPath);
        card.setCardHP(Integer.parseInt(cardHP));
        card.setCardAttack(Integer.parseInt(cardAttack));
        card.setCardType(Integer.parseInt(type));

        CardService service = new CardService();
        boolean result = service.addCard(card);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("result", result);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        resp.getWriter().println(jsonObj.toString());
    }
}
