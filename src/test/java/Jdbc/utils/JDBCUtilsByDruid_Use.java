package Jdbc.utils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class JDBCUtilsByDruid_Use {
    @Test
    public void testDML(){
        Connection connection = null;
        String sql =null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int column;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            System.out.println("更新前： ");
            sql = "select * from admin";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            column = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()){
                for (int i = 1; i <= column; i++){
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
            sql = "update admin set pwd=? where name=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1,"张泽灵" + "1");
            statement.setString(2,"张三");
            statement.executeUpdate();
            sql = "select * from admin";
            System.out.println("\n更新后： ");
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            column = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()){
                for (int i = 1; i <= column; i++){
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println(connection.getClass());
            JDBCUtilsByDruid.close(resultSet,statement,connection);
        }

    }

    @Test
    public void testSelectToArrayList(){
        System.out.println("使用druid方式完成");
        Connection connection = null;
        String sql = "select * from actor where id>=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,1);
            resultSet = preparedStatement.executeQuery();
            ArrayList<Actor> list = new ArrayList<>();
            while (resultSet.next()){
                Actor actor = new Actor();

                actor.setId(resultSet.getInt("id"));
//                actor.setNAME(resultSet.getString("NAME"));
                actor.setSex(resultSet.getString("sex"));
                actor.setPhone(resultSet.getString("phone"));
                System.out.println(actor);
                list.add(actor);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtilsByDruid.close(resultSet,preparedStatement,connection);
        }
    }
}
