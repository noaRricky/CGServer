package com.ricky.servlet;

import com.org.json.JSONException;
import com.org.json.JSONObject;
import com.ricky.model.Player;
import com.ricky.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForgetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String userID = req.getParameter("userID");

        UserService service = new UserService();
        Player player = service.getPlayer(userID);
        if (player != null) {
            String question = player.getQuestion();
            String answer = player.getAnswer();

            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");
            try {
                //把验证信息你封装成JSONObject对象

                JSONObject jsonObj = new JSONObject();
                jsonObj.put("result", "true");
                jsonObj.put("userID", userID);
                jsonObj.put("question", question);
                jsonObj.put("answer", answer);
                //输出相应
                resp.getWriter().println(jsonObj.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("utf-8");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", "false");
            resp.getWriter().println(jsonObject.toString());
        }
    }
}
