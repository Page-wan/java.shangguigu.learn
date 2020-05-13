package test;

/**
 * @program: java.shangguigu.learn
 * @description: 异常测试
 * @author: Hao Peng
 * @create: 2019-12-03 20:14
 **/
public class ErrorTest {
    public static void mian(String[] args) {
        Student s = new Student();
        s.setId(-100);//这样写是有错的
    }
}


class Student{
    private int id;
    public void setId(int id) throws RuntimeException{
        if (id > 0){
            this.id = id;
        }else{
            throw new RuntimeException("输入数据非法");
        }
    }
}
