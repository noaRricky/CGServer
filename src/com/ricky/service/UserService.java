package com.ricky.service;

import com.ricky.model.Player;
import com.ricky.model.User;
import com.ricky.util.SQLHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    //=================参数区域===============
    private SQLHelper helper = null;
    private ResultSet rs = null;

    public UserService(){}

    /**
     * 登录功能：根据用户名密码查询用户信息
     * @param userID 用户名
     * @param password 密码
     * @return 如果用户存在返回user,否则返回null
     */
    public User login(String userID, String password)
    {
        helper = new SQLHelper();
        User user = null;

        String sql = "SELECT * FROM user WHERE UserID=? AND Password=?";
        Object parameter[] = {userID, password};

        try {
            rs = helper.query(sql, parameter);

            if (rs != null)
            {
                while (rs.next()) {
                    user = new User();
                    user.setUserID(rs.getString(1));
                    user.setUserName(rs.getNString(2));
                    user.setPassword(rs.getString(3));
                    user.setType(rs.getInt(4));
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally {
            if (rs != null)
            {
                helper.close();
                rs = null;
            }
            helper = null;
        }

        return user;
    }

    /**
     * 更新用户数据
     * @param userID 用户ID
     * @param username 用户名
     * @param password 密码
     * @param question 密保问题
     * @param answer 密保答案
     * @return 更新成功返回true，否则返回false
     */
    public boolean updatePlayer(String userID, String username, String password,
                                String question, String answer) {
        helper = new SQLHelper();
        boolean result = false;

        String sql = "UPDATE user SET UserName = ?, Password=?, Question=?, Answer=? WHERE UserID=?";
        Object[] parameter = {username, password, question, answer, userID};

        result = helper.execute(sql, parameter);
        helper = null;

        return result;
    }

    /**
     * 注册玩家信息
     * @param player 玩家信息
     * @return 注册成功返回true,否则返回false
     */
    public boolean register(Player player)
    {
        helper = new SQLHelper();
        boolean flag;

        String sql = "INSERT INTO user(UserID, UserName, Password," +
                " Type, Question, Answer, RegTime) \n" +
                "VALUES (?,?,?,?,?,?,now())";
        Object parameter[] = {player.getUserID(), player.getUserName(), player.getPassword(),
                            player.getType(), player.getQuestion(), player.getAnswer()};

        flag = helper.execute(sql, parameter);
        helper = null;

        return flag;
    }

    /**
     * 修改用户密码
     * @param userID 用户名
     * @param password 密码
     * @return 如果修改成功返回true,否则返回false
     */
    public boolean updatePassword(String userID, String password)
    {
        helper = new SQLHelper();
        boolean flag;

        String sql = "UPDATE user SET Password=? WHERE UserID=?";
        Object parameter[] = {password, userID};

        flag = helper.execute(sql, parameter);
        helper = null;

        return flag;
    }

    /**
     * 获取玩家的所有信息
     * @param userID 用户ID
     * @return 如果该用户ID存在返回所有信息，否则返回null
     */
    public Player getPlayer(String userID)
    {
        helper = new SQLHelper();
        Player player = null;

        String sql = "SELECT * FROM user WHERE UserID=?";
        Object parameter[] = {userID};

        try {
            rs = helper.query(sql, parameter);

            if (rs != null) {
                while (rs.next())
                {
                    player = new Player();
                    player.setUserID(rs.getString(1));
                    player.setUserName(rs.getNString(2));
                    player.setPassword(rs.getString(3));
                    player.setType(rs.getInt(4));
                    player.setQuestion(rs.getNString(5));
                    player.setAnswer(rs.getNString(6));
                    player.setDate(rs.getDate(7).toString());
                    player.setTime(rs.getTime(7).toString());
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally {
            if (rs != null)
            {
                helper.close();
                rs = null;
            }
            helper = null;
        }

        return player;
    }
}
