package collection.learn;

import org.junit.Test;

import javax.naming.ldap.SortResponseControl;
import javax.swing.text.html.HTMLDocument;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * @program: java.shangguigu.learn
 * @description: 集合接口声明的方法
 * @author: Hao Peng
 * @create: 2020-01-02 16:22
 **/
public class ColletionTest {
    @Test
    public void test1(){
        Collection coll = new ArrayList();
        coll.add("Aa");
        coll.add("BB");
        coll.add(123);//自动装箱
        coll.add(new Date());
        //size()：获取添加的元素的个数
        System.out.println(coll.size());
        System.out.println(coll.toArray().toString());
        //addAll(Collection coll1):将coll1集合中的元素添加到当前的集合中
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add("CC");
        coll.addAll(coll1);
        System.out.println(coll.size());
        System.out.println(coll);
        //isEmpty()：判断当前集合是否为空
        System.out.println(coll.isEmpty());
        //clear：清空集合元素
        //coll.clear();
        System.out.println(coll.size());
        System.out.println("*****************");
        //contains(Object obj);判断当前集合中是否包含obj，内容相同用
        coll.add(new person("Jerry", 25));
        coll.add(new String("Tom"));
        System.out.println(coll.contains(new String("Tom")));//true
        //要想输出true，需要在person类中重写equals方法
        System.out.println(coll.contains(new person("Jerry",25)));//false
        //同containsAll()
        Collection coll2 = Arrays.asList(123,456);
        System.out.println(coll.containsAll(coll2));
        //remove(Object obj)

    }

    @Test
    public void test2(){
        //remove(Object obj)
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(new person("Jerry",25));
        System.out.println(coll.remove(123));//true
        System.out.println(coll);
        //同removeAll(Object obj)；移除obj和coll共同的元素
    }
    @Test
    public void test3(){
        //retainAll(Object obj);求两者交集,并对当前集合进行修改
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(new person("Jerry",25));
        Collection coll1 = Arrays.asList(123,456,789);
        coll.retainAll(coll1);
        System.out.println(coll);
        //集合->数组：toArray()
        Object[] arr = coll.toArray();
        for (Object obj : arr) {
            System.out.println(obj);
        }
        //数组->集合asList()
        Collection coll4 = Arrays.asList(arr);
        System.out.println(coll4.getClass());
        //asList()需要注意的地方
        List arr1 = Arrays.asList(new int[]{1,2,3});
        System.out.println(arr1.size());//1
        List arr2 = Arrays.asList(new Integer[]{1,2,3});
        System.out.println(arr2.size());//3
        //iterator():返回Iterator接口的实例,用于遍历集合元素

    }

    @Test
    public void test4(){
        //iterator():返回Iterator接口的实例,用于遍历集合元素
        //迭代器是设计模式的一种
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(new person("Jerry",25));
        Iterator iterator = coll.iterator();
        //通常用迭代器遍历集合的方式：
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //foreach()增强循环也可以
        //需要注意增强for循环，只是把集合中的元素取出来，如果取出来
        //赋值，对元集合不会改变
        String[] strs = new String[]{"MM","MM"};
        for (String s :
                strs) {
            s = "GG";
        }
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);//MM
        }
    }
    @Test
    public void test5(){
        //List中的常用方法
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add(new String("Tom"));
        list.add(new person("Jerry",25));
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        for (Object obj :
                list) {
            System.out.println(obj);
        }
        for (int i = 0; i <list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void test6(){
        LinkedHashMap map = new LinkedHashMap();
        map.put(123,22);
        map.put(456,33);
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println(map.get(123));
        TreeSet set = new TreeSet();
        set.add(123);
        set.add(22);
        Iterator iterator1 = set.iterator();
        while (iterator1.hasNext()){
            System.out.println(iterator1.next());
        }
    }

    @Test
    public void tes77t() throws IOException {
        Properties pros = new Properties();
        //此前需要在当前工程创建一个名叫jdbc.properties的file或者名叫jdbc的Resource Bundle的配置文件
        FileInputStream fis = new FileInputStream("D:\\JAVA_project\\java.shangguigu.learn\\jdbc.properties");
        pros.load(fis);
        String name = pros.getProperty("name");
        String password = pros.getProperty("password");
        System.out.println("name:" + name + "+password:" + password);
        fis.close();
    }
    @Test
    public void test33(){
        List src = new ArrayList();
        src.add(123);
        src.add(78);
        src.add(49);
        //IndexOutOfBoundsException: Source does not fit in dest
        //List dest = new ArrayList();
        //Collections.copy(dest,src);
        //正确的写法
        List dest = Arrays.asList(new Object[src.size()]);
        Collections.copy(dest,src);
    }
}

class person{
    private String name;
    private int age;
    public person() {
    }
    public person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

