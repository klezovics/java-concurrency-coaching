package com.klezovich.concurrency.creation.visibility;

import lombok.SneakyThrows;

public class TestVisibility {


    public static void main(String[] args) {

        var vv = new VolatileVisibility();
        var tWriter = new WriterThread(vv);
        var tReader = new ReaderThread(vv);
        tReader.setPriority(Thread.MAX_PRIORITY);
        tWriter.setPriority(Thread.MIN_PRIORITY);

        tReader.start();
        tWriter.start();
    }

    static abstract class VisTestThread extends Thread {

        protected VolatileVisibility vv;

        protected VisTestThread(VolatileVisibility vv) {
            super();
            this.vv = vv;
        }
    }

    static class ReaderThread extends VisTestThread {

        protected ReaderThread(VolatileVisibility vv) {
            super(vv);
        }

        @Override
        public void run() {
            vv.readerThread();
        }
    }

    static class WriterThread extends VisTestThread {

        protected WriterThread(VolatileVisibility vv) {
            super(vv);
        }

        @Override
        public void run() {
              vv.writerThread();
        }
    }


    public static class VolatileVisibility {

        boolean flag = true;
        //This variable is not declared volatile, so on some multicore computer the
        //value might get out of sync.
        int counter = 0;

        @SneakyThrows
        public void writerThread() {
            while (true) {
                counter++;
                System.out.println("Writer value:" + counter);
                Thread.sleep(100);
            }
        }

        @SneakyThrows
        public void readerThread() {
            while (true) {
                System.out.println("Reader value: " + counter);
                Thread.sleep(100);
            }
        }
    }
}
