package Jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class Jdbc_DML {

    private Scanner in = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        new Jdbc_DML().insert();
        new Jdbc_DML().insert();
        new Jdbc_DML().update();
        new Jdbc_DML().selete();
        new Jdbc_DML().delete();
    }
    private void selete() throws Exception{
        System.out.println("请输入要查询的用户");
        String name = in.next();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url,user,password);
        String sql = "select * from admin where name=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,name);

        ResultSet resultSet = statement.executeQuery();
        int column = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()){
            for (int i = 1; i <= column; i++){
                System.out.print(resultSet.getString(i) + '\t');
            }
            System.out.println();
        }
        statement.close();
        connection.close();
    }
    private void insert() throws Exception{
        System.out.println("请输入要添加的用户");
        String name = in.next();
        System.out.println("请输入添加用户的密码");
        String pwd = in.next();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url,user,password);
        String sql = "insert into admin VALUES (?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,name);
        statement.setString(2,pwd);
        int row  = statement.executeUpdate();
        System.out.println(row>0? "insert successfully!": "insert failed!");
        statement.close();
        connection.close();
    }

    private void update()throws Exception {
        System.out.println("请输入要更新的用户");
        String name = in.next();
        System.out.println("请输入用户的新密码");
        String pwd = in.next();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url,user,password);
        String sql = "update  admin set pwd = ?  where name=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,pwd);
        statement.setString(2,name);
        int row  = statement.executeUpdate();
        System.out.println(row>0? "update successfully!": "update failed!");
        statement.close();
        connection.close();
    }

    private void delete() throws Exception{
        System.out.println("请输入要删除的用户");
        String name = in.next();
        System.out.println("请输入删除用户的密码");
        String pwd = in.next();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url,user,password);
        String sql = "delete from admin where name=? and pwd=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,name);
        statement.setString(2,pwd);
        int row  = statement.executeUpdate();
        System.out.println(row>0? "delete successfully!": "delete failed!");
        statement.close();
        connection.close();
    }

}
