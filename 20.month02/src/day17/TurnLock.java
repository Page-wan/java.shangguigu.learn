package day17;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: java.shangguigu.learn
 * @description: 自旋锁
 * @author: Hao Peng
 * @create: 2020-02-17 23:18
 **/
public class TurnLock {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public void myLock(){//自定义上锁
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t come in");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnlock(){//自定义释放锁
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t 释放锁");
        atomicReference.compareAndSet(thread,null);
    }

    public static void main(String[] args) {
        /*
        线程t1上锁，然后隔5秒释放锁，确保是t1先拿到锁，然后在t1拿到锁1秒之后t2自旋等待锁
         */
        TurnLock turnLock = new TurnLock();
        new Thread(() -> {
            turnLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
            turnLock.myUnlock();
        },"t1").start();

        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        new Thread(() -> {
            turnLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            turnLock.myUnlock();
        },"t2").start();
    }
}
