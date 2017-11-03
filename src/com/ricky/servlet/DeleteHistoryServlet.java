package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.service.GHService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteHistoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("historyNum");
        int historyNum = Integer.parseInt(str);

        GHService service = new GHService();
        boolean result = service.deleteHistory(historyNum);

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("result", result);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println(jsonObj.toString());
    }
}
