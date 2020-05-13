package test;

/**
 * @program: java.shangguigu.learn
 * @description: 多线程创建方法
 * @author: Hao Peng
 * @create: 2019-12-08 21:42
 **/
public class mythread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            if (i % 2 == 0){
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        mythread mythread = new mythread();
        mythread.start();
        Thread.currentThread().setName("主线程");
        for (int i = 0; i < 100; i++){
            if (i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
