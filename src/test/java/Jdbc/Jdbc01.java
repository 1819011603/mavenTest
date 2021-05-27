package Jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Jdbc01 {
    public static void main(String[] args) throws SQLException {
        // 前置工作： 在项目下创建一个libs目录
        // 将mysql.jar 拷贝到该目录下，点击 add to project .. 加入到项目中

//    1. 注册驱动
        Driver driver = new com.mysql.jdbc.Driver();
        /***
         * jdbc:mysql:// 规定好的表示协议，通过jdbc的方式连接mysql
         * localhost表示主机，可以是IP地址
         * 3306 表示mysql监听的端口
         * actor表示连接到mysql dbms 的名为“actor.properties”的数据库
         * mysql的连接本质就是 socket连接
         */
        String url = "jdbc:mysql://localhost:3306/actor.properties";

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","123456");
        Connection connection = driver.connect(url,properties);

//        执行sql
//        String sql = "insert into actor.properties values(null,'刘德华','男','1970-11-11','110')";
//        String sql = "update  actor.properties set name='周星驰' where id=1";
        String sql = "delete  from actor where id=1";
        // Statement 执行静态SQL语句 存在SQL注入风险：
        // 没有对用户输入的数据进行充分的检查，而在用户输入数据中注入非法的SQL语句，恶意攻击数据库、
        // SQL语句已预编译并存储在PreparedStatement对象中。 然后可以使用该对象多次有效地执行此语句。
        /**
         * sql注入
         *
         * 正常： select * from admin where name='name' and pwd='pwd'
         *
         * sql注入：
         * 用户名 为   1' or
         * 密码 为    or '1'= '1
         * select * from admin where name='1'  or  ' and pwd=' or '1'='1' 三个or成立一个即可
         */
        //使用PreparedStatement替代Statement
        // CallableStatement <存储过程>
        Statement statement = connection.createStatement();
        int row = statement.executeUpdate(sql);// 返回影响行数
        System.out.println(row > 0 ? "成功": "失败");
//        关闭连接
        statement.close();
        connection.close();
    }


}
