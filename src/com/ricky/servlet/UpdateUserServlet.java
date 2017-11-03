package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.model.User;
import com.ricky.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        String userID = req.getParameter("userID");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String question = req.getParameter("question");
        String answer = req.getParameter("answer");

        UserService service = new UserService();
        boolean result = service.updatePlayer(userID, username, password, question, answer);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("result", result);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println(jsonObj.toString());
    }
}
