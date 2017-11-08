package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.model.GameHistory;
import com.ricky.service.GHService;
import com.ricky.util.ModelUri;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateHistoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        GameHistory history = new GameHistory(
                Integer.parseInt(req.getParameter(ModelUri.HISTORY_NUM)),
                req.getParameter(ModelUri.PLAYER_A),
                req.getParameter(ModelUri.PLAYER_B),
                req.getParameter(ModelUri.WINNER),
                req.getParameter(ModelUri.DATE),
                req.getParameter(ModelUri.TIME)
        );

        GHService service = new GHService();
        boolean flag = service.updateHistory(history);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ModelUri.RESULT, flag);

        resp.getWriter().println(jsonObject.toString());
    }
}