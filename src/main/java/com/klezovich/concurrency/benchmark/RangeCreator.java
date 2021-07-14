package com.klezovich.concurrency.benchmark;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class RangeCreator {

    private List<Range> ranges = new ArrayList<>();

    RangeCreator(long from, long to, int segmentCount) {
        long segmentLength = (to - from)/segmentCount + 1;

        long start = from;
        long end = from + segmentLength;

        for (int ii=1; ii<=segmentCount; ii++) {
            ranges.add(new Range(start, end));
            start = end;
            end = end + segmentLength;
        }
    }

}
