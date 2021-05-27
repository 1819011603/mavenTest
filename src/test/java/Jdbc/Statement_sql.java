package Jdbc;

import org.junit.Test;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.Stack;

public class Statement_sql {

  /*sql注入：
1' or
or '1'= '1
    */
    public static void main(String[] args)throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入管理员的名字：");
        String admin_name = scanner.nextLine();
        String admin_pwd = scanner.nextLine();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url,user,password);

        Statement statement = connection.createStatement();
        System.out.println("Statement sql注入: ");
        String sql = "select * from actor where name='"+ admin_name + "'and sex='" + admin_pwd + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String sex = resultSet.getString(3);
            Date borndate = resultSet.getDate(4);
            System.out.println(id + "   " + name + '\t' + sex + '\t' + borndate);
        }
        sql = "select * from actor where name=? and sex=?";


        //预编译】
        // 这里并未进行单引号用\反斜杠做了转义 不正确的使用其实还有可能出现SQL注入
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("PreparedStatement 无sql注入: ");
        // prepareStatement对象防止sql注入的方式是把用户非法输入的单引号用\反斜杠做了转义，从而达到了防止sql注入的目的
        preparedStatement.setString(1, admin_name);
        preparedStatement.setString(2, admin_pwd);


        // 执行查询不加sql  因为已经预编译过了
        ResultSet resultSet1 = preparedStatement.executeQuery();
        int column = resultSet1.getMetaData().getColumnCount();
        while (resultSet1.next()){
//            for (int i = 1;  i < column; i++){
//                System.out.println(resultSet1.getString(i));
//            }
            int id = resultSet1.getInt(1);
            String name = resultSet1.getString(2);
            String sex = resultSet1.getString(3);
            Date borndate = resultSet1.getDate(4);
            System.out.println(id + "   " + name + '\t' + sex + '\t' + borndate);
        }
        resultSet.close();
        statement.close();
        connection.close();

        new Statement_sql().PreparedStatement_();
    }

    /*sql注入：
1' or
or '1'= '1
  */

    public void PreparedStatement_()throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入管理员的名字：");
        String admin_name = scanner.nextLine();
        String admin_pwd = scanner.nextLine();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url,user,password);

        Statement statement = connection.createStatement();
        System.out.println("Statement sql注入: ");
        String sql = "select * from actor where name='"+ admin_name + "'and sex='" + admin_pwd + "'";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String sex = resultSet.getString(3);
            Date borndate = resultSet.getDate(4);
            System.out.println(id + "   " + name + '\t' + sex + '\t' + borndate);
        }

        //预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("PreparedStatement sql注入: ");


        ResultSet resultSet1 = preparedStatement.executeQuery();
        int column = resultSet1.getMetaData().getColumnCount();
        while (resultSet1.next()){
//            for (int i = 1;  i < column; i++){
//                System.out.println(resultSet1.getString(i));
//            }
            int id = resultSet1.getInt(1);
            String name = resultSet1.getString(2);
            String sex = resultSet1.getString(3);
            Date borndate = resultSet1.getDate(4);
            System.out.println(id + "   " + name + '\t' + sex + '\t' + borndate);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
