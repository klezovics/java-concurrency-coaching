package com.klezovich.concurrency.creation;

public class ThreadCounter {

    public static void main(String[] args) {
        var t1 = new Thread(ThreadCounter::countToTen);
        var t2 = new Thread(ThreadCounter::countToTen);

        t1.setName("T1");
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setName("T2");
        t2.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
    }

    private static void countToTen() {
        for (int ii=1;ii<=10;ii++) {
            System.out.println(Thread.currentThread().getName() + ": count " + ii);
        }
    }
}
