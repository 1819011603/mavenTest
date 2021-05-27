package Jdbc;

import Jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Batch_ {
    @Test
    public void noBatch()throws Exception{
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 Values(null,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            statement.setString(1,"jack");
            statement.setString(2,"jackPassword");
            statement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("传统方式耗时: "+ (end-start)/1000);// 将近4分钟
        statement.close();
        connection.close();
    }

    @Test
    public void Batch()throws Exception{
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 Values(null,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for(int i = 1; i <= 5000; i++){
            statement.setString(1,"jack");
            statement.setString(2,"jackPassword");
            statement.addBatch();
            if(i % 1000 == 0){
                statement.executeBatch();
                statement.clearBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("批量方式耗时: "+ (end-start));
        statement.close();
        connection.close();
    }

}
