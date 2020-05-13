package day17;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: java.shangguigu.learn
 * @description: 读写锁
 * @author: Hao Peng
 * @create: 2020-02-17 23:46
 **/

class Resource{
    private volatile Map<Integer,String> map = new HashMap<>();
    ReentrantReadWriteLock rwlock =  new ReentrantReadWriteLock();
    public void write(Integer k, String v){
        rwlock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 开始写入数据" + k);
            map.put(k,v);
            try {
                TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwlock.writeLock().unlock();
        }
    }

    public void read(Integer k){
        rwlock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 开始读取数据" + k);
            try {
                TimeUnit.MILLISECONDS.sleep(400);} catch (InterruptedException e) {e.printStackTrace();}
            String v = map.get(k);
            System.out.println(Thread.currentThread().getName() + "\t 读取数据完成："  + v);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwlock.readLock().unlock();
        }
    }
}
public class ReadWriteLock {
    public static void main(String[] args) {
        Resource resource = new Resource();
        for (int i = 1; i <= 5; i++){
            final int tempInt = i;
            new Thread(() -> {
                resource.write(tempInt,tempInt + "");
            },String.valueOf(i)).start();
        }

        //try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

        for (int i = 1; i <= 5; i++){
            final int tempInt = i;
            new Thread(() -> {
                resource.read(tempInt);
            },String.valueOf(i)).start();
        }
    }
}
