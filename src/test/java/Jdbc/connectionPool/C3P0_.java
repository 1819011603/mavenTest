package Jdbc.connectionPool;

import Jdbc.utils.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 演示c3p0的使用
 */
public class C3P0_ {
    @Test
    public void testC3P0() throws Exception{
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        // 给数据源设置相关参数
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        //初始化连接数
        comboPooledDataSource.setInitialPoolSize(10);

        //最大连接数
        comboPooledDataSource.setMaxPoolSize(50);

        // 测试效率 连接释放5000次
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();

        System.out.println("c3p0 5000次耗时： " + (end-start));
        // 测试是否可以连接
        Connection connection = comboPooledDataSource.getConnection();

        System.out.println("连接ok");

        connection.close();




    }

    @Test
    public void testC3P0_02() throws Exception{
        ComboPooledDataSource comboPooledDataSource = JDBCUtils.getComboPooledDataSource("actor");
        long start = System.currentTimeMillis();
        System.out.println("获取50000连接...");
        for (int i = 0; i < 500000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时： " + (end-start));//4668

    }
}
