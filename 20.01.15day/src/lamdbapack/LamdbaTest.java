package lamdbapack;

import org.junit.Test;

import java.util.Comparator;

/**
 * @program: java.shangguigu.learn
 * @description: Lamdba测试
 * @author: Hao Peng
 * @create: 2020-01-16 09:40
 **/
public class LamdbaTest {

    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱成都");
            }
        };
        r1.run();
        System.out.println("****************");
        Runnable r2 = () -> System.out.println("我爱四川");

        r2.run();
    }

    @Test
    public void test2(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return Integer.compare(integer,t1);
            }
        };
        int compare1 = com1.compare(12,33);
        System.out.println(compare1);
        System.out.println("****************");
        //Lambda表达式的写法
        Comparator<Integer> com2 = (integer, t1) -> Integer.compare(integer,t1);
        int compare2 = com1.compare(42,33);
        System.out.println(compare2);
        System.out.println("****************");
        //方法引用
        Comparator<Integer> com3 = Integer::compare;
        int compare3 = com1.compare(42,33);
        System.out.println(compare3);
    }
}
