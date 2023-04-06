package com.atguigu.kylin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author layne
 */
public class KylinTest {
    public static void main(String[] args) throws Exception {

        //Kylin_JDBC 驱动
        String KYLIN_DRIVER = "org.apache.kylin.jdbc.Driver";

        //Kylin_URL
        String KYLIN_URL = "jdbc:kylin://zgg:7070/FirstProject";

        //Kylin的用户名
        String KYLIN_USER = "ADMIN";

        //Kylin的密码
        String KYLIN_PASSWD = "KYLIN";

        //添加驱动信息
        Class.forName(KYLIN_DRIVER);

        //获取连接
        Connection connection = DriverManager.getConnection(KYLIN_URL, KYLIN_USER, KYLIN_PASSWD);

        //预编译SQL
        PreparedStatement ps = connection.prepareStatement("select dname,sum(sal) from emp e join dept d on e.deptno = d.deptno group by dname");

        //执行sql
        ResultSet resultSet = ps.executeQuery();

        //遍历打印
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1)+":"+resultSet.getDouble(2));
        }

    }
}
