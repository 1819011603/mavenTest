package Jdbc.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * 这是一个工具类，完成对mysql的连接和关闭资源
 */
public class JDBCUtils {
    private static String user;
    private static String password;
    private static String driver;
    private static String url;
    static {
        try {

            Properties properties = new Properties();
            properties.load(new FileInputStream("src/mysql.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");

        }catch (Exception e){
            // 1.将编译异常转成运行异常
           throw new RuntimeException(e);
        }

    }
    public static Connection getConnection(){
        try {

            return DriverManager.getConnection(url,user,password);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
    public static void close(ResultSet resultSet, Statement statement,Connection connection){
        try {
            if(resultSet!=null){
                resultSet.close();
            }
            if(statement!=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        }catch (Exception e){
            throw new RuntimeException();
        }

    }

    // 这是因为c3p0配置文件生产ComboPoolDataSource在我这台机器上失效 这是我自定义生产的 配置文件生产的方法
    // 在src\c3p0-config目录下 定义一个Properties文件即可使用
    public static ComboPooledDataSource getComboPooledDataSource()throws Exception{
        return getComboPooledDataSource(null);
    }
    public static ComboPooledDataSource getComboPooledDataSource(String name)throws Exception{
        if(name == null){
            name = "c3p0-Config";
        }
        name += ".properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(System.getProperty("user.dir") + File.separator + "src\\c3p0-Config" + File.separator + name));
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        driver = properties.getProperty("driverClass");
        url = properties.getProperty("jdbcUrl");
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);
        comboPooledDataSource.setInitialPoolSize(Integer.parseInt(properties.getProperty("initialPoolSize","20")));

        comboPooledDataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("maxPoolSize","100")));

        comboPooledDataSource.setMaxIdleTime( Integer.parseInt(properties.getProperty("maxIdleTime","30")));
        comboPooledDataSource.setMaxStatements(Integer.parseInt(properties.getProperty("maxStatements","5")));
        comboPooledDataSource.setAcquireIncrement(Integer.parseInt(properties.getProperty("acquireIncrement","5")));
        comboPooledDataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("minPoolSize","10")));
        return comboPooledDataSource;

    }

}
