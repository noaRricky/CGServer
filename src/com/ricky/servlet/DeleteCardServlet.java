package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.service.CardService;
import com.ricky.util.ModelUri;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCardServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cardID = Integer.parseInt(req.getParameter(ModelUri.CARD_ID));

        CardService service = new CardService();
        boolean ret= service.deleteCard(cardID);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ModelUri.RESULT, ret);

        resp.getWriter().println(jsonObject.toString());
    }
}
