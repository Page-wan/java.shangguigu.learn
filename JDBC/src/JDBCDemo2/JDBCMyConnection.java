package JDBCDemo2;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @program: java.shangguigu.learn
 * @description: 自己的链接和关闭操作
 * @author: Hao Peng
 * @create: 2020-02-25 16:11
 **/
public class JDBCMyConnection {

    public static Connection getResource() throws Exception{
        //获取系统加载器
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbcinfo.properties");
        Properties properties = new Properties();
        properties.load(is);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");
        //2、加载驱动
        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void closeResource(Connection connection, Statement ps){
        //7、资源关闭
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(Connection connection, Statement ps, ResultSet re){
        //7、资源关闭
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (re != null) {
                re.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
