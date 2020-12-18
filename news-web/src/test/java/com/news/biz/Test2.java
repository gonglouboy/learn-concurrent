package com.news.biz;

import java.util.stream.Stream;

/**
 * TODO 类实现描述
 *
 * @author 78328
 * @since 2020年12月18日 下午6:39:41
 */
public class Test2 {
    private final static Object lock = new Object();

    public static void main(String[] args) {
        Stream.of("线程1", "线程2").forEach(n -> new Thread(n) {
            public void run() {
                Test2.testWait();
            }
        }.start());
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    private static void testWait() {
        synchronized (lock) {
            try {
                lock.wait(10_000);
                System.out.println("wait结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
