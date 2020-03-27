package com.suj.lang.concurrent.executor;

import org.apache.log4j.Logger;

import java.util.concurrent.*;

/**
 * Created by sujayjayaram on 20/01/2016.
 *
 * Don't want to do:
 * public class MyExecutorCompletionService implements Executor {}
 * as this specifies just the command:
 * public void execute(Runnable command) {}
 * i.e. not Futures etc.
 *
 * Don't want to do
 * public class MyExecutorCompletionService implements Executor {}
 * Because CompletionService is better.
 * ThreadPoolExecutor and ScheduledThreadPoolExecutor implements
 * Executor and ExecutorService interfaces
 *
 * Nice lambda syntax
 * // Callable c = () -> "Test";
 */
public class MyExecutorCompletionService<V> implements CompletionService<V> {

    static Logger log = Logger.getLogger(MyExecutorCompletionService.class);

    // ArrayBlockingQueue needs a capacity arg, LinkedBlockingQueue does not!
    // private BlockingQueue<FutureTask<V>> tasksTODO = new ArrayBlockingQueue<FutureTask<V>>(100);
    private BlockingQueue<FutureTask<V>> tasksTODO = new LinkedBlockingQueue<FutureTask<V>>();
    private BlockingQueue<FutureTask<V>> tasksCompleted = new LinkedBlockingQueue<FutureTask<V>>();

    // Allows us to create all the threads and then set them off in one go.
    // A CyclicBarrier is similar with subtle differences.
    // See http://tutorials.jenkov.com/java-util-concurrent/cyclicbarrier.html
    // CyclicBarrier allows us to trigger a runnable when all threads arrive at
    // the barrier. (CyclicBarriers may be reused whereas CountdownLatches may not)
    private CountDownLatch startLatch;

    public MyExecutorCompletionService(long numThreads) {
        try{
            startLatch = new CountDownLatch((int)numThreads + 1); // one extra for this thread

            for(int i = 0; i < numThreads; i++) {
                log.debug("Creating thread " + i);
                (new MyThread(tasksTODO, tasksCompleted, "Thread" + i, startLatch)).start();
            }

            // Wait for all threads to be waiting on the latch
            // The count will initially be numThreads + 1 but each
            // thread calls countDown() and then await() meaning
            // that by the time they have all done this, the latch
            // count will be down to one and the last startLatch.countDown()
            // will start all the threads off.
            log.debug("Waiting for all threads to wait on latch");
            long count = startLatch.getCount();
            while (count != 1){ // count == 1 means its just this thread that needs to countDown() as all Threads will countDown() on this latch when they start running.
                log.debug("Count = " + count);
                Thread.sleep(1000);
                count = startLatch.getCount();
            }

            // Start all threads ...
            log.debug("releasing latch");
            startLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public Future<V> submit(Callable<V> task) {
        log.debug("submit() called");
        FutureTask<V> future = new FutureTask<>(task);
        return submitFuture(future);
    }

    private Future<V> submitFuture(FutureTask<V> future) {
        try {
            tasksTODO.put(future); // Blocks if the Queue has a capacity constraint (which it does not)!
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

        return future;
    }

    @Override
    public Future<V> submit(Runnable task, V result) {
        log.debug("submit() called");
        FutureTask<V> future = new FutureTask<>(task, result);
        return submitFuture(future);
    }

    @Override
    public Future<V> take() throws InterruptedException {
        return tasksCompleted.take();
    }

    @Override
    public Future<V> poll() {
        return tasksCompleted.poll();
    }

    @Override
    public Future<V> poll(long timeout, TimeUnit unit) throws InterruptedException {
        return tasksCompleted.poll(timeout, unit);
    }
}
