package compareTest;

import org.junit.Test;

import java.util.Arrays;

/**
 * @program: java.shangguigu.learn
 * @description: 比较器接口的学习
 * @author: Hao Peng
 * @create: 2019-12-20 22:18
 **/

/*

说明：java中的对象，正常情况下，只能比较==或!=。不能使用>或<
可以实现两个接口中的一个：Comparable或Comparator
IDEA中快速生成构造器（toString,compareTo等方法）的快捷键：Alt + Insert
 */
public class CompareLearn {
    /*
    Comparable接口的使用
    1、像String、包装类等实现了Comparable接口，重写了compareTo()，因此能比较
    2、重写compareTo(Obj)的规则
        如果当前对象this大于形参对象obj,则返回正数
        如果当前对象this小于形参对象obj,则返回负整数
        如果当前对象this等于形参对象obj,则返回0
     3、对于自定义类来说。如果需要排序，我们可以让自定义类实现Comparable接口，重写compareTo()
            在方法中指明如何排序
     */
    @Test
    public void test1(){
        String[] arr = new String[]{"aa","ff","zz","bb"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //对自己定义的商品对象进行排序
    @Test
    public void test2(){
        Goods[] arr = new Goods[4];
        arr[0] = new Goods("LenovoMouse",35);
        arr[1] = new Goods("DellMouse",43);
        arr[2] = new Goods("LogitechMouse",443);
        arr[3] = new Goods("HuaweiMouse",99);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
