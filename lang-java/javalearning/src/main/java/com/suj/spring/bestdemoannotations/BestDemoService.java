package com.suj.spring.bestdemoannotations;

import com.suj.spring.annotations2.CustomerDAO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by sujayjayaram on 21/02/2016.
 */
// Annotate to tell Spring to examine this class at startup
// Without this annotation we get the exception:
// Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'customerService' is defined
@Component("bestDemoServiceComponent")
public class BestDemoService
{
    //@Autowired (both do the same thing)
    @Resource
    BestDemoDAO bestDemoDAO;

    // Not needed anymore
//    public void setCustomerDAO(CustomerDAO customerDAO) {
//        this.customerDAO = customerDAO;
//    }

    @Override
    public String toString() {
        return "BestDemoService [bestDemoDAO=" + bestDemoDAO + "]";
    }

}
