package com.klezovich.concurrency.creation;

import lombok.SneakyThrows;

public class IncrementDecrement {

    public static void main(String[] args) {
        var counter = new Counter();

        var inc = new IncrementerThread(counter);
        var dec = new DecrementerThread(counter);

        inc.start();
        dec.start();
    }


    static abstract class WorkerThread extends Thread {
        protected final Counter counter;

        WorkerThread(Counter counter) {
            this.counter = counter;
            this.setName(this.getClass().getSimpleName());
        }

        public void start() {
            System.out.println("Starter thread " + this.getName());
            super.start();
        }
    }

    static class IncrementerThread extends WorkerThread {

        IncrementerThread(Counter counter) {
            super(counter);
            this.setPriority(MAX_PRIORITY);
        }

        @SneakyThrows
        public void run() {
            while (true) {
                var val = this.counter.increment();
                System.out.println("Counter value is " + val);
                Thread.sleep(5);
            }
        }
    }

    static class DecrementerThread extends WorkerThread {
        DecrementerThread(Counter counter) {
            super(counter);
        }

        @SneakyThrows
        public void run() {
            while (true) {
                var val = this.counter.decrement();
                System.out.println("Counter value is " + val);
                Thread.sleep(5);
            }
        }
    }

    static class Counter {
        private volatile int counter =0;

        synchronized int increment() {
            counter++;
            return counter;
        }

        synchronized int decrement() {
            counter--;
            return counter;
        }
    }
}
