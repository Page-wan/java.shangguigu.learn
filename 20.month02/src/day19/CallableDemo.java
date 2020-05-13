package day19;

import java.util.concurrent.Callable;

/**
 * @program: java.shangguigu.learn
 * @description: Callable测试
 * @author: Hao Peng
 * @create: 2020-02-19 22:28
 **/
public class CallableDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();
        //Thread没有传Callable接口的构造方法。
        //Thread(Runnable target, String name) //Allocates a new Thread object.
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}
