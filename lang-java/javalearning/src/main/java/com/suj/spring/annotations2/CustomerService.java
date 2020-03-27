package com.suj.spring.annotations2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by sujayjayaram on 21/02/2016.
 */
// Annotate to tell Spring to examine this class at startup
// Without this annotation we get the exception:
// Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'customerService' is defined
@Component
public class CustomerService
{
    //@Autowired (both do the same thing)
    @Resource
    CustomerDAO customerDAO;

    // Not needed anymore
//    public void setCustomerDAO(CustomerDAO customerDAO) {
//        this.customerDAO = customerDAO;
//    }

    @Override
    public String toString() {
        return "CustomerService [customerDAO=" + customerDAO + "]";
    }

}
