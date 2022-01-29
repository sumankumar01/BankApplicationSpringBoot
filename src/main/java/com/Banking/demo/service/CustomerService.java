package com.Banking.demo.service;

import com.Banking.demo.payload.CustomerDTO;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(long id);
    CustomerDTO UpdateCustomerPost(CustomerDTO customerDTO,Long id);
    void  deletePost(long id);
}
