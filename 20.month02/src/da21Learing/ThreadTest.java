package da21Learing;

import java.util.concurrent.*;

/**
 * @program: java.shangguigu.learn
 * @description: 多线程学习
 * @author: Hao Peng
 * @create: 2020-02-21 19:39
 **/
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());

        //此时最大线程数即为maximumPoolSize + capacity数
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 线程执行");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}

