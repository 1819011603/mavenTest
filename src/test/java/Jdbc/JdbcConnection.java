package Jdbc;

import org.jetbrains.annotations.TestOnly;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// 方式4： 使用Class.forName 自动完成注册驱动 简化代码  推荐使用方式4
public class JdbcConnection {
    // 方式一  静态加载
    @Test
    public void connect01() throws SQLException{
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/actor.properties";

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","123456");
        Connection connection = driver.connect(url,properties);
        System.out.println("方式一： " + connection);
        connection.close();
    }

    // 方式二 使用反射加载 动态加载，更加灵活
    @Test
    public void connect02() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class myclass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) myclass.newInstance();
        String url = "jdbc:mysql://localhost:3306/actor.properties";

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","123456");
        Connection connection = driver.connect(url,properties);
        System.out.println("方式二： " + connection);
        connection.close();
    }
    // DriverManager替代Driver  统一管理
    @Test
    public void connect03() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class myclass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) myclass.newInstance();
        String url = "jdbc:mysql://localhost:3306/actor.properties";

        String user = "root";
        String password = "123456";
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println("方式三： " + connection);
        connection.close();
    }


    // 方式4： 使用Class.forName 自动完成注册驱动 简化代码  推荐使用方式4
    @Test
    public void connect04() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        // 在加载Driver类时 自动注册
        /**
         * 源码：类加载static执行 因此加载和注册底层已经完成了
         *   static {
         *         try {
         *             DriverManager.registerDriver(new Driver());
         *         } catch (SQLException var1) {
         *             throw new RuntimeException("Can't register driver!");
         *         }
         *     }
         */

        // Class.forName("com.mysql.jdbc.Driver");可省去
        /**
         * mysql驱动5.16  jdbc4以后都不需要调用Class.forName
         * 自动调用mysql-connector-java-5.1.49-bin.jar包下  META-INF\services\java.sql.Driver文本中的类名称去注册
         *
         * com.mysql.jdbc.Driver
         * com.mysql.fabric.jdbc.FabricMySQLDriver
         *
         * 建议不要省去
         */
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://localhost:3306/actor.properties";

        String user = "root";
        String password = "123456";

        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println("方式四： " + connection);
        connection.close();
    }


    /**
     * 配置文件 硬编程不灵活  让连接mysql更灵活  最好的方式
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Test
    public void connect05() throws IOException, ClassNotFoundException, SQLException {
        // 通过Properties对象获取配置文件信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println("方式五: " + connection);
    }


}
