package com.suj.lang.concurrent.executor;

import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by sujayjayaram on 20/01/2016.
 */
public class MyTask implements Callable<String>  {

    static Logger log = Logger.getLogger(MyTask.class);

    @Override
    public String call() throws Exception {
        //note a single Random object is reused here
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(20);

        log.debug("Sleeping for " + randomInt + " secs");
        Thread.currentThread().sleep(1000*randomInt);
        log.debug("Sleep over!");

        return Integer.toString(randomInt);
    }
}
