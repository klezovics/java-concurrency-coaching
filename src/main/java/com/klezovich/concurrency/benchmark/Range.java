package com.klezovich.concurrency.benchmark;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Range {

    private long from;
    private long to;

    Range(long from, long to) {
        this.from = from;
        this.to = to;
    }

    public long getLength() {
        return to - from;
    }
}
