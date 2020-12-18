package com.news.biz;

import java.util.stream.Stream;

/**
 * TODO 类实现描述
 *
 * @author 78328
 * @since 2020年12月18日 下午6:47:15
 */
public class Test1 {
    private final static Object lock = new Object();

    public static void main(String[] args) {
        Stream.of("线程1", "线程2").forEach(n -> new Thread(n) {
            public void run() {
                Test1.testWait();
            }
        }.start());
    }

    private static void testWait() {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName()
                        + "正在执行");
                lock.wait(10_000);
                System.out.println(Thread.currentThread().getName()
                        + "wait结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
