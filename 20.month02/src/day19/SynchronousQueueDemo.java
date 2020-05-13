package day19;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: java.shangguigu.learn
 * @description: SynchronousQueue测试
 * @author: Hao Peng
 * @create: 2020-02-19 18:50
 **/
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue<String> blockingDeque = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println("线程" + Thread.currentThread().getName() + "\tput 1");
                blockingDeque.put("1");
                System.out.println("线程" + Thread.currentThread().getName() + "\tput 2");
                blockingDeque.put("2");
                System.out.println("线程" + Thread.currentThread().getName() + "\tput 3");
                blockingDeque.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"thread1").start();

        new Thread(() -> {
            try {
                try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("线程" + Thread.currentThread().getName() + "\t获得：" + blockingDeque.take());
                try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("线程" + Thread.currentThread().getName() + "\t获得：" + blockingDeque.take());
                try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println("线程" + Thread.currentThread().getName() + "\t获得：" + blockingDeque.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"thread2").start();
    }
}
