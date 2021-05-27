package Jdbc.utils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtils_Use {
    @Test
    public void testDML(){
        Connection connection = JDBCUtils.getConnection();
        String sql =null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int column;
        try {
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
            statement.setString(1,"张泽灵");
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
            JDBCUtils.close(resultSet,statement,connection);
        }

    }
}
