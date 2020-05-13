package JDBCDemo2;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: java.shangguigu.learn
 * @description: 查询操作
 * @author: Hao Peng
 * @create: 2020-02-25 16:58
 **/
public class selectDemo {

    //执行查询操作
    @Test
    public void selectTest() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCMyConnection.getResource();
            String sql = "select id,name,email,birth from myuser where id = ?";
            ps = conn.prepareStatement(sql);
            //填充占位符
            ps.setObject(1,2);
            //执行并返回结果
            resultSet = ps.executeQuery();
            //处理结果
            //next()函数判断结果集的下一条是否有数据，如果有返回true,并且指向吓一跳数据
            if (resultSet.next()) {
                //获取档当前这条数据的各个字段
                int id = resultSet.getInt(1);
                String name = resultSet.getNString(2);
                String email = resultSet.getNString(3);
                String birth = resultSet.getNString(4);
                //将结果封装为一个对象
                User user = new User(id, name, email, birth);
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCMyConnection.closeResource(conn,ps,resultSet);
        }
    }


    @Test
    public void selectTest1(){
        String sql1 = "select id,name,email,birth from myuser";
        List<User> list1 = selectAll(User.class,sql1);
        list1.forEach(System.out::println);
        System.out.println("***********");
        String sql2 = "select id,name,email,birth from myuser where id < ?";
        List<User> list2 = selectAll(User.class,sql2,3);
        list2.forEach(System.out::println);
    }
    //针对user表建立通用查询方法
    public <T> List<T> selectAll(Class<T> clazz,String sql, Object ...args) {
        Connection conn = null;
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
            JDBCMyConnection.closeResource(conn,ps,rs);
        }
        return null;
    }
}

