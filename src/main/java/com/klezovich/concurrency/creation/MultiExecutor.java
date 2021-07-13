package com.klezovich.concurrency.creation;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    // Add any necessary member variables here
    private final List<Thread> threads = new ArrayList<>();
    /* 
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        for (Runnable task : tasks) {
            threads.add(new Thread(task));
        }
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        for (Thread thread : threads) {
            thread.start();
        }
    }
}