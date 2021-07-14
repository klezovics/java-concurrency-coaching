package com.klezovich.concurrency.benchmark;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeCreatorTest {


    @Test
    void testSegmentCreation() {
        var segments = new RangeCreator(1,100,50).getRanges();
        for (var segment : segments ) {
            System.out.println(segment);
        }
    }
}