package Jdbc.utils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

public class ReflectActor1 {
    @Test
    public void testActor1()throws Exception{
        Object obj;
        Map<String,String[]> map = new HashMap<>();
        Connection connection = JDBCUtilsByDruid.getConnection();
        String sql = "select * from actor1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int count = metaData.getColumnCount();
        if(resultSet.next()){
            try {
                obj =Actor1.class.newInstance();
                for (int i = 1; i <=count ; i++){
                    map.put(metaData.getColumnName(i),new String[]{resultSet.getString(metaData.getColumnName(i))});
                    System.out.println(metaData.getColumnName(i) + " " + resultSet.getString(metaData.getColumnName(i)));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(map);
    }
}
