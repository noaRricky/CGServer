package com.ricky.util;

import java.sql.*;
import java.util.concurrent.ConcurrentNavigableMap;

public class SQLHelper {

    //============此处为静态变量区域======================
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/zsh?useSSL=false";
    private static String user = "ricky";
    private static String password = "950928";

    private Connection conn = null;
    private PreparedStatement preStat = null;
    private ResultSet rs = null;

    /*
    构造sqlHelper类
     */
    public SQLHelper(){
    }

    /**
     * 加载驱动，只需要加载一次
     */
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 获取mysql的一个connection对象
     * @return 链接成功返回connection对象，否则return null
     */
    public static Connection getConnection()
    {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally {
            return conn;
        }
    }

    /**
     *用于执行增删改操作
     * @param sql sql语句
     * @param parameter sql语句中?所代表的参数
     * @return 操作成功返回true,否则返回false
     */
    public boolean execute(String sql, Object[] parameter)
    {
        conn = getConnection();
        boolean flag = false;

        try {
            preStat = conn.prepareStatement(sql);

            if (parameter != null)
            {
                for (int i = 0; i < parameter.length; i++)
                {
                    preStat.setObject(i + 1, parameter[i]);
                }
            }

            preStat.execute();
            flag = true;
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally {
            close();
        }

        return flag;
    }

    /**
     * 对数据库的增删改操作进行批量处理
     * @param sql sql语句
     * @param objects 二维数组，表示批量操作的重要信息
     * @return 操作成功返回true, 否则返回false
     */
    public boolean executeBatch(String sql, Object[][] objects)
    {
        conn = getConnection();
        boolean flag = false;

        try {
            preStat = conn.prepareStatement(sql);
            conn.setAutoCommit(false);

            if (objects != null)
            {
                for (int i = 0; i < objects.length; i++)
                {
                    for (int j = 0; j < objects[i].length; j++)
                    {
                        preStat.setObject(j + 1, objects[i][j]);
                    }
                    preStat.addBatch();
                }
            }

            preStat.executeBatch();  //执行完该语句后表示插入成功
            conn.commit(); //进行手动提交

            flag = true;

            conn.setAutoCommit(true);  //提交完后恢复现场将auto commit 设为true
        } catch (SQLException e)
        {
            e.printStackTrace();

            if (conn != null)
            {
                try {
                    conn.rollback();   //当发生异常状况时使用回滚，防止脏数据产生
                    conn.setAutoCommit(true); //处理完后恢复县城
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        } finally {
            close();
        }

        return flag;
    }

    /**
     * 执行查询语句
     * @param sql sql语句
     * @param parameters sql语句中?所代表的参数
     * @return 返回查询结果
     */
    public ResultSet query(String sql, Object[] parameters)
    {
        conn = getConnection();

        try {
            preStat = conn.prepareStatement(sql);

            if (parameters != null)
            {
                for (int i = 0; i < parameters.length; i++)
                {
                    preStat.setObject(i + 1, parameters[i]);
                }
            }

            rs = preStat.executeQuery();

        } catch (SQLException e)
        {
            e.printStackTrace();
            close();
        }

        return rs;
    }

    /**
     * 关闭连接数据库的所有标签
     */
    public void close()
    {
        try {
            if (rs != null)
            {
                rs.close();
                rs = null;
            }
            if (preStat != null)
            {
                preStat.close();
                preStat = null;
            }
            if (conn != null)
            {
                conn.close();
                conn = null;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public boolean test()
    {
        conn = getConnection();
        boolean result;

        if (conn == null)
        {
            result = false;
        }
        else
        {
            result = true;
        }

        try {
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public static void main(String[] args)
    {
        SQLHelper helper = new SQLHelper();

        String sql = "SELECT * FROM card";
        String[] parameters = null;
        ResultSet rs = null;

        try {
            rs = helper.query(sql, parameters);

            while (rs.next())
            {
                int cardNumber = rs.getInt(1);
                String cardName = rs.getNString(2);

                System.out.println("number: " + cardNumber + " name: " + cardName);
            }


        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally {

            helper.close();
        }
    }
}
