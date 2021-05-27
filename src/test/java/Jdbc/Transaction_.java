package Jdbc;

import Jdbc.utils.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;
import org.junit.Test;

import java.sql.*;

public class Transaction_ {

    public void not_transaction() throws Exception{
        Connection connection = JDBCUtils.getConnection();
        String sql = "update account set balance=? where name=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int column;
        try {
            statement = connection.prepareStatement(sql);
            statement.setDouble(1,2800);
            statement.setString(2,"马云");
            statement.executeUpdate();
            double i = 1/0;
            statement.setDouble(1,10200);
            statement.setString(2,"马化腾");
            statement.executeUpdate();

        }catch (Exception e){
            throw new RuntimeException();
        }finally {
            sql = "select * from account";
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
            JDBCUtils.close(resultSet,statement,connection);
        }
    }

    @Test
    public void transaction() throws Exception{
        Connection connection = JDBCUtils.getConnection();
        connection.setAutoCommit(false);
        String sql = "update account set balance=? where name=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Savepoint savepoint = null;
        savepoint = connection.setSavepoint("1");
        int column;
        try {

            statement = connection.prepareStatement(sql);
            statement.setDouble(1,2100);
            statement.setString(2,"马云");
            statement.executeUpdate();
//            double i = 1/0;
            statement.setDouble(1,10900);
            statement.setString(2,"马化腾");
            statement.executeUpdate();
            connection.commit();
        }catch (Exception e){
            connection.rollback(savepoint);
            throw new RuntimeException();
        }finally {
            sql = "select * from account";
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
            JDBCUtils.close(resultSet,statement,connection);
        }
    }
}
