package com.Banking.demo.controller;


import com.Banking.demo.payload.CustomerDTO;
import com.Banking.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apb/bank")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO)
    {
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.CREATED);
    }

   @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCusomer(@PathVariable(name = "id") Long id)
   {
       return ResponseEntity.ok(customerService.getCustomerById(id));
   }
}
