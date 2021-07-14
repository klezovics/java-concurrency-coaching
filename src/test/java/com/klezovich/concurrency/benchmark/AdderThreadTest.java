package com.klezovich.concurrency.benchmark;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

@Slf4j
class AdderThreadTest {

    @Test
    void testNoConcurrency() throws InterruptedException {
        var adder = new AdderThread(0L, 1000 * 1000 * 1000);
        adder.start();
        adder.join();

        log.info("Result is: {}", adder.getResult());
    }

    @Test
    void testTwoThreads() throws InterruptedException {
        var a1 = new AdderThread(0L, 500*1000*1000);
        var a2 = new AdderThread(500*1000*1000 + 1, 1000 * 1000 * 1000);
        a1.start();
        a2.start();
        a1.join();
        a2.join();

        log.info("Result is: {}", a1.getResult().add(a2.getResult()));
    }

    @Test
    void testFourThreads() throws InterruptedException {
        var a1 = new AdderThread(0L, 250*1000*1000);
        var a2 = new AdderThread(250*1000*1000 + 1, 500 * 1000 * 1000);
        var a3 = new AdderThread(500*1000*1000 + 1, 750 * 1000 * 1000);
        var a4 = new AdderThread(750*1000*1000 + 1, 1000 * 1000 * 1000);

        a1.start();
        a2.start();
        a3.start();
        a4.start();

        a1.join();
        a2.join();
        a3.join();
        a4.join();

        log.info("Result is: {}", a1.getResult().add(a2.getResult()).add(a3.getResult()).add(a4.getResult()));
    }

    @Test
    void testTenThreads() throws InterruptedException {
        var ranges = new RangeCreator(0L, 1000*1000*1000L, 10).getRanges();

        var adderThreads = new ArrayList<AdderThread>();
        for(var range : ranges) {
            adderThreads.add(new AdderThread(range.getFrom(),range.getTo()));
        }

        adderThreads.stream().forEach( at -> at.start() );
        adderThreads.stream().forEach( at -> {
            try {
                at.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


}