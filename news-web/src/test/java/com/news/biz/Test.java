package com.news.biz;

import java.util.stream.Stream;

/**
 * https://zhuanlan.zhihu.com/p/86717279
 *
 * @author 78328
 * @since 2020年12月18日 下午6:42:49
 */
public class Test {
    private final static Object lock = new Object();

    public static void main(String[] args) {
        Stream.of("线程1", "线程2").forEach(n -> new Thread(n) {
            public void run() {
                Test.testSleep();
            }
        }.start());
    }

    //sleep方法休眠之后，
    private static void testSleep() {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName()
                        + "正在执行");
                Thread.sleep(10_000);
                System.out.println(Thread.currentThread().getName()
                        + "休眠结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
