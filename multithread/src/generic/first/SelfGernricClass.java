package generic.first;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: java.shangguigu.learn
 * @description: 自定义泛型类
 * @author: Hao Peng
 * @create: 2020-01-07 18:00
 **/
public class SelfGernricClass{
    @Test
    public void test122(){
        CustomertDAO dao = new CustomertDAO();
        dao.add(new Customert());
        List<Customert> list = dao.getForList(10);
    }
    @Test
    public void test(){
        List<Object> list1 = null;
        List<String> list2 = null;
        //list1 = list2;//报错
    }
}

class DAO<T>{//操作表的类
    //data(base) access object
    public void add(T t){}//增
    public boolean remove(){return false;}//删
    public void update(){}//改
    public List<T> getForList(int index){return null;}//查
}
class Customert{}
class CustomertDAO extends DAO<Customert>{}


