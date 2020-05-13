package JDBCByDAO;

import JDBCDemo2.User;

import java.sql.Connection;
import java.util.List;

public interface UserDAO {
    //将cust对象添加到数据库中
    void insert(Connection conn, User user);

    //将指定id的数据删除
    void deleteById(Connection conn, int id);

    //针对内存的User对象，去修改数据库中的指定记录
    void upade(Connection conn, User user);

    //查询表中的所有记录构成的集合
    List<User> getAll(Connection conn);

    //返回数据表中数据的条目数
    Long getUser(Connection conn);

}
