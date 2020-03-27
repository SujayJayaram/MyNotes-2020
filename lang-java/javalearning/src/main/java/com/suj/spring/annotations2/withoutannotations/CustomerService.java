package com.suj.spring.annotations2.withoutannotations;

/**
 * Created by sujayjayaram on 21/02/2016.
 */
public class CustomerService
{
    CustomerDAO customerDAO;

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public String toString() {
        return "CustomerService [customerDAO=" + customerDAO + "]";
    }

}
