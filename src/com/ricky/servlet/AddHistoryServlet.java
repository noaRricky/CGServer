package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.service.GHService;
import com.ricky.util.ModelUri;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AddHistoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String playerA = req.getParameter(ModelUri.PLAYER_A);
        String playerB = req.getParameter(ModelUri.PLAYER_B);
        String winner = req.getParameter(ModelUri.WINNER);
        String date = req.getParameter(ModelUri.DATE);
        String time = req.getParameter(ModelUri.TIME);
        String datetime = date + " " + time;


        GHService service = new GHService();
        boolean result = service.addHistory(playerA, playerB, winner, datetime);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("result", result);

        resp.getWriter().println(jsonObj);
    }
}
