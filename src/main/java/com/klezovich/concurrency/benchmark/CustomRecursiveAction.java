package com.klezovich.concurrency.benchmark;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

@Slf4j
public class CustomRecursiveAction extends RecursiveAction {

    private Range workload;
    private static final int THRESHOLD = 200*1000*1000;

    public CustomRecursiveAction(Range workload) {
        this.workload = workload;
    }

    @Override
    protected void compute() {

        log.info("Computing {}", workload);

        if (workload.getLength() > THRESHOLD) {
            ForkJoinTask.invokeAll(createSubtasks());
        } else {
           processing(workload);
        }
    }

    private List<CustomRecursiveAction> createSubtasks() {
        List<CustomRecursiveAction> subtasks = new ArrayList<>();

        var partOne = new Range(workload.getFrom(),workload.getFrom()+ (workload.getTo() - workload.getFrom())/2);
        var partTwo = new Range(workload.getFrom()+ (workload.getTo() - workload.getFrom())/2 + 1,workload.getTo());

        subtasks.add(new CustomRecursiveAction(partOne));
        subtasks.add(new CustomRecursiveAction(partTwo));

        return subtasks;
    }

    private void processing(Range work) {
        var tmp = BigInteger.ZERO;

        for(var ii = work.getFrom(); ii<=work.getTo(); ii++) {
            tmp=tmp.add(BigInteger.valueOf(ii));
        }
    }
}