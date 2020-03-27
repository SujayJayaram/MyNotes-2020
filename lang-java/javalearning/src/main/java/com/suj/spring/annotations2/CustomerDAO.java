package com.suj.spring.annotations2;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by sujayjayaram on 21/02/2016.
 */

// Annotate to tell Spring to examine this class at startup
@Component
public class CustomerDAO
{
    @Override
    public String toString() {
        return "Hello , This is CustomerDAO";
    }
}
