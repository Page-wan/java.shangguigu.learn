package JDBCDemo1;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @program: java.shangguigu.learn
 * @description: JDBC入门
 * @author: Hao Peng
 * @create: 2020-02-25 13:01
 **/
public class myJDBC {
    @Test
    public void testConnection() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/studenttest";
        //将用户名和密码封装在Properties中
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    @Test
    public void test2() throws Exception {
        //1、获取Driver实现类对象，使用反射
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver)clazz.newInstance();
        String url = "jdbc:mysql://localhost:3306/studenttest";
        //将用户名和密码封装在Properties中
        Properties info = new Properties();
        info.setProperty("user","root");
        info.setProperty("password","123456");
        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    @Test
    public void test3() throws Exception{
       //1、获取Driver实现类对象
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver)clazz.newInstance();
        //2、提供另外三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/studenttest";
        String user = "root";
        String password = "123456";
        //注册驱动
        DriverManager.registerDriver(driver);
        //获取链接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
    @Test
    public void test4() throws Exception{
        //1、提供另外三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/studenttest";
        String user = "root";
        String password = "123456";

        //2、获取Driver实现类对象
        //在Mysql的Driver实现类Driver.class中会执行静态代码块，帮助我们注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Driver driver = (Driver)clazz.newInstance();
        //注册驱动
        //DriverManager.registerDriver(driver);

        //获取链接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test
    public void test5() throws Exception{
        //1、读取配置文件中的四个信息
        //创建流
        InputStream is = myJDBC.class.getClassLoader().getResourceAsStream("jdbcinfo.properties");
        Properties properties = new Properties();
        properties.load(is);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");
        //2、加载驱动
        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
