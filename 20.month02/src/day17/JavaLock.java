package day17;

/**
 * @program: java.shangguigu.learn
 * @description: java各种锁
 * @author: Hao Peng
 * @create: 2020-02-17 23:02
 **/



class Phone {
    public synchronized void sendMail(){
        System.out.println("线程" + Thread.currentThread().getName() + "执行sendMail操作");
        sendmessage();
    }

    public synchronized void sendmessage(){
        System.out.println("线程" + Thread.currentThread().getName() + "执行sendmessage操作");
    }
}

public class JavaLock {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendMail();
        },"t1").start();

        new Thread(() -> {
            phone.sendMail();
        },"t2").start();
    }
}
