package JDBCDemo2;

/**
 * @program: java.shangguigu.learn
 * @description:
 * @author: Hao Peng
 * @create: 2020-02-25 23:24
 **/
public //创建一个javaBean将数据库中每条信息作为bean的一个对象
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
