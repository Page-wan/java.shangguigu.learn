package day22;

import java.util.concurrent.TimeUnit;

/**
 * @program: java.shangguigu.learn
 * @description: 死锁测试
 * @author: Hao Peng
 * @create: 2020-02-22 20:31
 **/
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "aaa";
        String lockB = "bbb";
        new Thread(new Rs(lockA,lockB),"threadAAA").start();
        new Thread(new Rs(lockB,lockA),"threadBBB").start();
    }
}

class Rs implements Runnable {
    String lockA;
    String lockB;
    public Rs(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "获得锁：" + lockA + "尝试获得锁：" + lockB);
            try {
                TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "获得锁：" + lockB + "尝试获得锁：" + lockA);
            }
        }
    }
}