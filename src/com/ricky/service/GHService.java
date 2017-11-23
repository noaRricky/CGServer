package com.ricky.service;

import com.org.json.JSONArray;
import com.org.json.JSONObject;
import com.ricky.model.GameHistory;
import com.ricky.util.SQLHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GHService {

    //========================基本参数区域==========================
    private SQLHelper helper = null;
    private ResultSet rs = null;

    //==================共有函数区域=============================

    /**
     * 获取所有游戏历史的信息
     * @return 如果存在返回List<GameHistory>,否则返回null
     */
    public JSONArray getAllHistory() {

        helper = new SQLHelper();
        JSONArray histories = null;

        String sql = "SELECT * FROM history";

        try {
            rs = helper.query(sql, null);

            if (rs != null) {
                histories = new JSONArray();
                while (rs.next()) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("historyNum", rs.getInt(1));
                    jsonObj.put("playerA", rs.getNString(2));
                    jsonObj.put("playerB", rs.getNString(3));
                    jsonObj.put("winner", rs.getNString(4));
                    jsonObj.put("date", rs.getDate(5).toString());
                    jsonObj.put("time", rs.getTime(5).toString());

                    histories.put(jsonObj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                helper.close();
                rs = null;
            }
            helper = null;
        }

        return histories;
    }

    /**
     * 获取游戏次数
     * @param userID 用户ID号码
     * @return 获取成功返回对应次数，否则返回-1
     */
    public int countHistory(String userID)
    {
        String sql = "SELECT count(*) FROM history WHERE PlayerB=? OR PlayerA=?";
        Object parameter[] = {userID, userID};

        return getTimes(sql, parameter);
    }

    public boolean addBattleHistory(String playerA, String playerB, String winner) {

        helper = new SQLHelper();
        boolean flag;

        String sql = "INSERT INTO history (PlayerA, PlayerB, Winner, Time) VALUES (?, ?, ?, now());";
        Object parameter[] = {playerA, playerB, winner};

        flag = helper.execute(sql, parameter);
        helper = null;

        return flag;
    }

    /**
     * 添加历史信息
     * @param playerA 玩家A
     * @param playerB 玩家B
     * @param winner 胜利者
     * @param datetime 对战时间
     * @return 添加成功返回true,否则返回false
     */
    public boolean addHistory(String playerA, String playerB, String winner, String datetime) {

        helper = new SQLHelper();
        boolean flag;

        String sql = "INSERT INTO history(PlayerA, PlayerB, Winner, Time) VALUES (?,?,?,?)";
        Object parameter[] = {playerA, playerB, winner, datetime};

        flag = helper.execute(sql, parameter);
        helper = null;

        return flag;
    }

    /**
     * 删除历史记录
     * @param number 历史记录的编号
     * @return 删除成功返回true,否则返回false
     */
    public boolean deleteHistory(int number)
    {
        helper = new SQLHelper();
        boolean flag;

        String sql = "DELETE FROM history WHERE HistoryNum=?";
        Object parameter[] = {number};

        flag = helper.execute(sql, parameter);
        helper = null;

        return flag;
    }

    /**
     * 更新历史信息
     * @param history 历史信息
     * @return 修改成功返回true,否则返回false
     */
    public boolean updateHistory(GameHistory history) {
        helper = new SQLHelper();
        boolean flag;

        String sql = "UPDATE history SET PlayerA = ?, PlayerB = ?, Winner = ?, Time = ? WHERE HistoryNum = ?";
        Object parameter[] = {history.getPlayerA(), history.getPlayerB(), history.getWinner(), history.getDateTime(),
                                history.getNumber()};

        flag = helper.execute(sql, parameter);
        helper = null;

        return flag;
    }

    /**
     * 根据玩家用户名，获取胜利次数
     * @param player 玩家用户名
     * @return 查询成功返回对应数字，否则返回-1
     */
    public int getWinTimes(String player)
    {
        String sql = "SELECT count(*) FROM history WHERE Winner=?";
        Object parameter[] = {player};

        return getTimes(sql, parameter);
    }

    /**
     * 根据玩家用户名获取失败次数
     * @param player 玩家用户名
     * @return 获取成功返回对应次数，否则返回-1
     */
    public int getLoseTimes(String player)
    {
        String sql = "SELECT count(*) FROM history WHERE (PlayerA=? OR PlayerB=?) AND Winner!=?;";
        Object parameter[] = {player, player, player};

        return getTimes(sql, parameter);
    }


    //=================私有函数区域=============================

    /**
     * 根据sql语句和参数获取次数
     * @param sql sql语句
     * @param parameter 参数
     * @return 获取成功返回次数，否则返回-1
     */
    protected int getTimes(String sql, Object[] parameter)
    {
        helper = new SQLHelper();
        int time = -1;

        try {
            rs = helper.query(sql, parameter);

            while (rs.next())
            {
                time = rs.getInt(1);
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

        return time;
    }

    public static void main(String args[])
    {
        GHService ghService = new GHService();

        ghService.addHistory("ricky", "tomoya", "ricky", "2015-10-05 13:43:00");
    }
}
