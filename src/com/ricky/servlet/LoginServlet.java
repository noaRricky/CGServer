package com.ricky.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.org.json.JSONException;
import com.org.json.JSONObject;
import com.ricky.model.User;
import com.ricky.service.UserService;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginServlet extends HttpServlet {

    private static List<String> userList = new ArrayList<>();

    private UserService userService = null;

    private static final int ONLINE = 2;
    private static final int PLAYER = 1;
    private static final int ADMIN = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        try {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("username", "ricky");
            jsonObj.put("password", "sws67772597");

            resp.getWriter().println(jsonObj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        final String userID = req.getParameter("userID");
        final String password = req.getParameter("password");

        int userType = -1;

        if (userList.contains(userID)) {
            userType = ONLINE;
        } else {
            userService = new UserService();
            User user = userService.login(userID, password);

            if (user != null)
            {
                userType = user.getType();
                //此处添加session保存用户登录信息
                synchronized (userList) {
                    userList.add(userID);
                }
            }
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        //把验证信息userID封装成JSONObject
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userID", userID);
        jsonObj.put("userType", userType);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(10 * 60 * 1000);
                    //线程等待10分钟后删除对应玩家数据
                    synchronized (userList) {
                        userList.remove(userID);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //输出响应
        resp.getWriter().println(jsonObj.toString());
    }
}
