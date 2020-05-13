package atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @program: java.shangguigu.learn
 * @description: ABA问题测试
 * @author: Hao Peng
 * @create: 2020-02-15 23:03
 **/
public class ABAatomicTest {
    static AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<>(100,0);
    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = integerAtomicStampedReference.getStamp();
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            integerAtomicStampedReference.compareAndSet(100,101,integerAtomicStampedReference.getStamp(),integerAtomicStampedReference.getStamp() + 1);
            integerAtomicStampedReference.compareAndSet(101,100,integerAtomicStampedReference.getStamp(),integerAtomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "最初版本号：" + stamp + ",当前版本号：" + integerAtomicStampedReference.getStamp());
        },"t1").start();

        new Thread(() -> {
            int stamp = integerAtomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
            boolean isTrue = integerAtomicStampedReference.compareAndSet(100,2020,stamp,integerAtomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "线程获得最初版本号：" + stamp + "。是否修改成功：" + isTrue + "，现在版本号：" + integerAtomicStampedReference.getStamp());
        },"t2").start();
    }
}
