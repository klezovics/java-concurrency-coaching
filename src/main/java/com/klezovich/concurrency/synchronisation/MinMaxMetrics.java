package com.klezovich.concurrency.synchronisation;

public class MinMaxMetrics {
    
    private volatile long min;
    private volatile long max;
    private volatile long average;
    private long count;

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        min=Long.MAX_VALUE;
        max=Long.MIN_VALUE;
        average=0;
        count=0;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public synchronized void addSample(long newSample) {
        if(newSample < min) {
            min = newSample;
        }

        if(newSample > max) {
            max = newSample;
        }

        average = (average*count + newSample)/(count+1);
        count++;
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        return min;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        return max;
    }
}
