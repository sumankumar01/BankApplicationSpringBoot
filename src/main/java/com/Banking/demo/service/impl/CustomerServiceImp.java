package com.Banking.demo.service.impl;

import com.Banking.demo.Repository.CustomerRepository;
import com.Banking.demo.entity.Customer;
import com.Banking.demo.exception.ResourceNotFoundException;
import com.Banking.demo.payload.CustomerDTO;
import com.Banking.demo.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService {

    private CustomerRepository customerRepository;
    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer cust=mapToEntity(customerDTO);

        Customer newCust=customerRepository.save(cust);

        CustomerDTO custResponse=mapToDTO(newCust);

        return custResponse;
    }

    @Override
    public CustomerDTO getCustomerById(long id) {
        Customer newCust=customerRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Post","id",id));

    return mapToDTO(newCust);
    }

    @Override
    public CustomerDTO UpdateCustomerPost(CustomerDTO customerDTO, Long id) {
        return null;
    }

    @Override
    public void deletePost(long id) {

    }

    private CustomerDTO mapToDTO(Customer customer)
    {


        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNum(customer.getMobileNum());

        return customerDTO;
    }
    //convert entity to DTO
    private Customer mapToEntity(CustomerDTO customerDTO)
    {


        Customer cust=new Customer();
        cust.setId(customerDTO.getId());
        cust.setFirstName(customerDTO.getFirstName());
        cust.setLastName(customerDTO.getLastName());
        cust.setEmail(customerDTO.getEmail());
cust.setMobileNum(customerDTO.getMobileNum());
        return cust;
    }
}
