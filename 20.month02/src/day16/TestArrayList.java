package day16;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: java.shangguigu.learn
 * @description: 测试ArrayList线程不安全问题
 * @author: Hao Peng
 * @create: 2020-02-16 21:40
 **/
public class TestArrayList {
    public static void main(String[] args) {
        Map<String,String> list = new ConcurrentHashMap<>(1,3,4);

        for (int i = 1; i <= 100; i++){
            new Thread(() -> {
                //UUID.randomUUID().toString()用来生成数据库的主键id非常不错
                list.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);//java.util.ConcurrentModificationException
            },String.valueOf(i)).start();
        }
    }
}
