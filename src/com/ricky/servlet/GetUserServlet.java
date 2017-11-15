package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.model.Player;
import com.ricky.service.UserService;
import com.ricky.util.ModelUri;

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
            jsonObj.put(ModelUri.USER_ID, player.getUserID());
            jsonObj.put(ModelUri.USER_NAME, player.getUserName());
            jsonObj.put(ModelUri.PASSWORD, player.getPassword());
            jsonObj.put(ModelUri.QUESTION, player.getQuestion());
            jsonObj.put(ModelUri.ANSWER, player.getAnswer());
            jsonObj.put(ModelUri.DATE, player.getDate());
            jsonObj.put(ModelUri.TIME, player.getTime());

            resp.getWriter().println(jsonObj.toString());
        }
    }
}
