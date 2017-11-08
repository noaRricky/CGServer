package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.model.User;
import com.ricky.service.UserService;
import com.ricky.util.ModelUri;
import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        String userID = req.getParameter(ModelUri.USER_ID);
        String username = req.getParameter(ModelUri.USER_NAME);
        String password = req.getParameter(ModelUri.PASSWORD);
        String question = req.getParameter(ModelUri.QUESTION);
        String answer = req.getParameter(ModelUri.ANSWER);

        UserService service = new UserService();
        boolean result = service.updatePlayer(userID, username, password, question, answer);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(ModelUri.RESULT, result);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println(jsonObj.toString());
    }
}
