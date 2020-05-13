package JDBCDemo2;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @program: java.shangguigu.learn
 * @description: 批量操作
 * @author: Hao Peng
 * @create: 2020-02-25 21:29
 **/
public class InsertTest {

    //向表goods中批量插入数据
    @Test
    public void testInsert()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCMyConnection.getResource();
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <=20000 ; i++) {
                ps.setObject(1,"name" + i);
                ps.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCMyConnection.closeResource(conn,ps);
        }
    }
    //向表goods中批量插入数据方式改进
    @Test
    public void testInsert2()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCMyConnection.getResource();
            conn.setAutoCommit(false);
            String sql = "insert into goods(name)values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <=20000 ; i++) {
                ps.setObject(1,"name" + i);
                //1、攒sql
                ps.addBatch();
                if (i % 500 == 0) {
                    //2、执行batch
                    ps.executeBatch();
                    //3、清空batch
                    ps.clearBatch();
                }
            }
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println("花费时间为：" + (end-start) + "毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCMyConnection.closeResource(conn,ps);
        }
    }
}
