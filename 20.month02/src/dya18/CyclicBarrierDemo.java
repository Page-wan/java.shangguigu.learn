package dya18;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: java.shangguigu.learn
 * @description: CyclicBarrier测试
 * @author: Hao Peng
 * @create: 2020-02-18 22:00
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //public CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("********召唤神龙");
        });
        for (int i = 1; i <= 7; i++){
            new Thread(() -> {
                System.out.println("收集到第" + Thread.currentThread().getName() + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
