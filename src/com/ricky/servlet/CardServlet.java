package com.ricky.servlet;

import com.org.json.JSONArray;
import com.ricky.service.CardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户处理获取卡牌信息的servlet
 */
public class CardServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CardService service = new CardService();
        JSONArray jsonArray = service.getAllCard();

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        resp.getWriter().println(jsonArray.toString());
    }
}
