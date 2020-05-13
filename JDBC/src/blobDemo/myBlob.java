package blobDemo;

import JDBCDemo2.JDBCMyConnection;
import org.junit.Test;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @program: java.shangguigu.learn
 * @description: blob测试
 * @author: Hao Peng
 * @create: 2020-02-25 20:24
 **/
public class myBlob {

    //插入Blob类型数据
    @Test
    public void testInsert() throws Exception {
        Connection conn = JDBCMyConnection.getResource();
        String sql = "insert into myuser(name,email,birth,photo)values(?,?,?,?);";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setObject(1,"刘德华");
        ps.setObject(2,"liu@163.com");
        ps.setObject(3,"1971-7-14");
        FileInputStream is = new FileInputStream(new File("刘德华.png"));
        ps.setBlob(4,is);
        ps.execute();
        JDBCMyConnection.closeResource(conn,ps);
    }

    //查询Blob类型数据
    @Test
    public void testqQury() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            conn = JDBCMyConnection.getResource();
            String sql = "select id,name,email,birth,photo from myuser where id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,9);
            resultSet = ps.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getNString(2);
                String email = resultSet.getNString(3);
                String birth = resultSet.getNString(4);
                User user = new User(id, name, email, birth);
                System.out.println(user);

                Blob photo = resultSet.getBlob("photo");
                is = photo.getBinaryStream();
                fos = new FileOutputStream("C:\\Users\\ph\\Desktop\\新建文件夹\\刘德华.png");
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer,0,len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JDBCMyConnection.closeResource(conn,ps,resultSet);
        }
    }
}
class User{
    int id;
    String name;
    String email;
    String birth;

    public User(){

    }

    public User(int id, String name, String email, String birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBirth() {
        return birth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}