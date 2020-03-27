package com.suj.lang.concurrent.executor;

/**
 * Created by sujayjayaram on 21/01/2016.
 */
public class MyExecutorServiceTest {

    // @Test
    public void testSubmit() throws Exception {
        MyExecutorCompletionService<String> e = new MyExecutorCompletionService<String>(10);

        for (int i = 0; i < 10; i++)
            e.submit(new MyTask()); // MyTask implements Callable<String>


        // Do not exit here.
         Thread.currentThread().sleep(1000*60*60);
    }
}