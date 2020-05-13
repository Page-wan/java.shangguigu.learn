package JDBCByDAO;

import JDBCDemo2.JDBCMyConnection;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: java.shangguigu.learn
 * @description: DAO测试
 * @author: Hao Peng
 * @create: 2020-02-25 23:05
 **/
public abstract class BaseDAO {
    //考虑数据库事务的转帐操作
    //通用的增删改操作
    public int update(Connection conn, String sql, Object ...args) {
        PreparedStatement ps = null;
        try {
            //1、预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //2、填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            //3、执行
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4、资源关闭
            JDBCMyConnection.closeResource(null,ps);
        }
        return 0;
    }

    //针对user表建立通用查询方法
    //返回多个对象
    public <T> List<T> selectAll(Connection conn,Class<T> clazz,String sql, Object ...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCMyConnection.getResource();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            //获取结果集的元数据：ResultSetMetaData
            ResultSetMetaData metaData = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = metaData.getColumnCount();
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                //当有结果时再创建对象，且需要空参构造器，然后用set()方法去给对象赋值
                T t = clazz.getDeclaredConstructor().newInstance();
                //处理结果中的每一列
                for (int i = 0; i < columnCount; i++) {
                    Object columValue = rs.getObject(i + 1);
                    //获取每个列的列明
                    String columnName = metaData.getColumnName(i + 1);

                    //给user对象指定的columnName属性，赋值为columValue，通过反射
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,columValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCMyConnection.closeResource(null,ps,rs);
        }
        return null;
    }

    public <E> E getValue(Connection conn, String sql, Object ...args) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            resultSet = ps.executeQuery();
            if (resultSet.next()){
                return (E)resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCMyConnection.closeResource(null,ps,resultSet);
        }
        return null;
    }
}

