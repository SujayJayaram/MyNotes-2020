package com.suj.spring.bestdemoannotations;

import org.springframework.stereotype.Component;

/**
 * Created by sujayjayaram on 21/02/2016.
 */

// Annotate to tell Spring to examine this class at startup
@Component
public class BestDemoDAO
{
    @Override
    public String toString() {
        return "Hello , This is BestDemoDAO";
    }
}
