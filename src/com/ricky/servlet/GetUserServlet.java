package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.model.Player;
import com.ricky.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userID = req.getParameter("userID");

        UserService service = new UserService();

        Player player = service.getPlayer(userID);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        if (player != null) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("userID", player.getUserID());
            jsonObj.put("username", player.getUserName());
            jsonObj.put("password", player.getPassword());
            jsonObj.put("question", player.getQuestion());
            jsonObj.put("answer", player.getAnswer());
            jsonObj.put("datetime", player.getDate() + " " + player.getTime());

            resp.getWriter().println(jsonObj.toString());
        }
    }
}
