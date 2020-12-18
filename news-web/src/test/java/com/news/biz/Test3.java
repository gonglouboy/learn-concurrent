package com.news.biz;

/**
 * TODO 类实现描述
 *
 * @author 78328
 * @since 2020年12月18日 下午6:57:28
 */
public class Test3 {
    private final static Object lock = new Object();

    private static void testWait() {
        synchronized (lock) {
            try {
                System.out.println("我一直在等待");
                lock.wait();
                System.out.println("wait被唤醒了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void notifyWait() {
        synchronized (lock) {
            try {
                Thread.sleep(5_000);
                lock.notify();
                System.out.println("休眠5秒钟唤醒wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread() {//这个线程一直在等待
            public void run() {
                Test3.testWait();
            }
        }.start();
        new Thread() {//这个线程准备去唤醒
            public void run() {
                Test3.notifyWait();
            }
        }.start();
    }
}
