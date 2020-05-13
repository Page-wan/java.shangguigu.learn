package firstLearn;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: java.shangguigu.learn
 * @description: 双端检测单例模式
 * @author: Hao Peng
 * @create: 2020-02-15 16:49
 **/
public class MySingleton {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.compareAndSet(5,22) + ":" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,33) + ":" + atomicInteger.get());
    }

}
