package JDBCDemo2;

import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * @program: java.shangguigu.learn
 * @description: PrepatedStatement测试
 * @author: Hao Peng
 * @create: 2020-02-25 15:30
 **/
public class PreparedStatementDemo {
    //向myuser表中添加一条数据
    @Test
    public void testStatement1() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //1、读取配置文件中的4个基本信息
            InputStream is = ClassLoader.getSystemClassLoader()
                    .getResourceAsStream("jdbcinfo.properties");
            Properties properties = new Properties();
            properties.load(is);
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driverClass = properties.getProperty("driverClass");
            //2、加载驱动
            Class.forName(driverClass);
            //3、获取链接
            connection = DriverManager.getConnection(url, user, password);
            //4、预编译sql语句，返回PreparedStatement实例
            String sql = "insert into myuser(name,email,birth)values(?,?,?)";
            ps = connection.prepareStatement(sql);
            //5、填充占位符（以此避免SQL注入）
            ps.setString(1,"蔡徐坤");
            ps.setString(2,"123@qq.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("2020-02-25");
            ps.setDate(3,new Date(date.getTime()));
            //6、执行操作
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    }

    //修改myuser表中的一条记录
    @Test
    public void testUpdate() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接
             conn = JDBCMyConnection.getResource();
            //2、预编译sql语句，返回PreparedStatement的实例
            String sql = "update myuser set name = ? where id = ?";
            ps = conn.prepareStatement(sql);
            //3、填充占位符
            ps.setObject(1,"坤坤");
            ps.setObject(2,3);
            //4、执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、资源关闭
            JDBCMyConnection.closeResource(conn,ps);
        }
    }

    //通用的增删改操作
    public void update(String sql,Object ...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1、获取数据库链接
            conn = JDBCMyConnection.getResource();
            //2、预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //3、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            //4、执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、资源关闭
            JDBCMyConnection.closeResource(conn,ps);
        }
    }

    @Test
    public void updateStatement(){
        String sql = "delete from myuser where id = ?";
        update(sql,7);
    }
}
