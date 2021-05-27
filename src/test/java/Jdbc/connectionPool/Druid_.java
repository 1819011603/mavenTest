package Jdbc.connectionPool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class Druid_ {
    // 加入配置文件 将文件拷贝在src下
    // Properties
    @Test
    public void testDruid()throws Exception{
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/druid-config/druid.properties"));

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        long start = System.currentTimeMillis();
        System.out.println("获取500000连接...");
        for (int i = 0; i < 500000; i++) {
            Connection connection = dataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时： " + (end-start));//1632

    }
}
