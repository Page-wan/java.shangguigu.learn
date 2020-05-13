package JDBCDemo2;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @program: java.shangguigu.learn
 * @description: 事务学习
 * @author: Hao Peng
 * @create: 2020-02-25 22:05
 **/
public class transactionDemo {

    //通用的增删改操作
    public int update(String sql,Object ...args) {
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
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5、资源关闭
            JDBCMyConnection.closeResource(conn,ps);
        }
        return 0;
    }

    //考虑数据库事务的转帐操作
    //通用的增删改操作
    public void update(Connection conn, String sql,Object ...args) {
        PreparedStatement ps = null;
        try {
            //1、预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //2、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            //3、执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4、资源关闭
            JDBCMyConnection.closeResource(null,ps);
        }
    }

    @Test
    public void testUpdateWithTransaction() {
        Connection conn = null;
        try {
            conn = JDBCMyConnection.getResource();
            conn.setAutoCommit(false);
            String sql1 = "update user_table set balance = balance - 100 where name = ?";
            update(conn,sql1,"AA");

            //网络异常
            System.out.println(10 /0);
            String sql2 = "update user_table set balance = balance + 100 where name = ?";
            update(conn,sql2,"BB");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null)
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JDBCMyConnection.closeResource(conn,null);
        }
    }
}
