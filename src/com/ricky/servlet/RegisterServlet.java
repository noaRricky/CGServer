package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.model.Player;
import com.ricky.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        String userID = req.getParameter("userID");
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        String question = req.getParameter("question");
        String answer = req.getParameter("answer");
        Player player = new Player(userID, password, username,
                question, answer);
        boolean result;

        UserService service = new UserService();
        result = service.register(player);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("result", result);

        resp.getWriter().println(jsonObj.toString());

    }
}
