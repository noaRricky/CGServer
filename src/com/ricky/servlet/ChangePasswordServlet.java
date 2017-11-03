package com.ricky.servlet;

import com.org.json.JSONObject;
import com.ricky.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userID = req.getParameter("userID");
        String password = req.getParameter("new_password");

        UserService service = new UserService();
        Boolean result = service.updatePassword(userID, password);

        JSONObject jsonObj = new JSONObject();

        jsonObj.put("result", result);
        resp.getWriter().println(jsonObj.toString());
    }
}
