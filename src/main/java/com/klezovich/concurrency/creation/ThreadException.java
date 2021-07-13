package com.klezovich.concurrency.creation;

public class ThreadException {

    public static void main(String[] args) {

        var t1 = new Thread(() -> {throw new RuntimeException("OOM error");});
        t1.setName("Naughty thread");

        t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Exception in thread " + t.getName() );
                System.out.println("Exception is " + e.getMessage());
            }
        });

        t1.start();
    }
}
