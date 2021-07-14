package com.klezovich.concurrency.benchmark;

import lombok.Getter;

import java.math.BigInteger;

@Getter
public class AdderThread extends Thread {

    private long from;
    private long to;
    private BigInteger result = BigInteger.ZERO;

    AdderThread(long from, long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        for(var ii=from; ii<to; ii++) {
            result=result.add(BigInteger.valueOf(ii));
        }
    }
}
