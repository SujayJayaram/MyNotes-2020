package com.suj.lang.concurrent.executor;

import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

/**
 * Created by sujayjayaram on 20/01/2016.
 */
public class MyThread<V> extends Thread {
    static Logger log = Logger.getLogger(MyThread.class);

    private final BlockingQueue<FutureTask<V>> tasksTODO;
    private final BlockingQueue<FutureTask<V>> tasksCompleted;
    private final String name;
    private final CountDownLatch startLatch;

    public MyThread(BlockingQueue<FutureTask<V>> tasksTODO, BlockingQueue<FutureTask<V>> tasksCompleted, String name, CountDownLatch startLatch) {
        this.tasksTODO = tasksTODO;
        this.tasksCompleted = tasksCompleted;
        this.name = name;
        this.startLatch = startLatch;
    }

    @Override
    public void run() {
        log.debug(name + ": run() called.");
        try {
            log.debug(name + ": waiting on latch");

            // Decrement the count and then wait for the signal from the main thread.
            startLatch.countDown();
            startLatch.await();
            do {
                log.debug(name + ": getting next task....");
                FutureTask mytask = tasksTODO.take(); // *** Blocks ***

                log.debug(name + ": running task.");
                // The run() method is used to do the actual compute. The get() method will
                // block until this completes.
                mytask.run();
                log.debug(name + ": task completed.");

                tasksCompleted.put(mytask);
            }
            while(true);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
