package com.ricky.servlet;

import com.org.json.JSONArray;
import com.ricky.model.GameHistory;
import com.ricky.service.GHService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GHService service = new GHService();
        JSONArray histories = service.getAllHistory();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        if (histories != null) {
            resp.getWriter().println(histories.toString());
        }
    }
}
