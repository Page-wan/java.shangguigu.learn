package threadTest1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: java.shangguigu.learn
 * @description: 线程池创建线程
 * @author: Hao Peng
 * @create: 2019-12-16 21:19
 **/

class myThread1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
public class ThreadPool {
    public static void main(String[] args) {
        //1、提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        //设置线程池的属性
        //因为service是一个接口对象（ExecutorService接口类型的），因此要转化为类的对象
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        service1.setCorePoolSize(2);

        //2、执行指定的线程的操作，需要提供实现Runnable接口或Callable接口的类的对象作为参数传递进去
        service.execute(new myThread1());//适用于Runnable实现类
        //service.submit(Callable callable);适合于Callable实现类
        //3、关闭连接池
        service.shutdown();
    }
}
