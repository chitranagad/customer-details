package com.customer.CustomerDetails.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.customer.CustomerDetails.model.Customer;
import com.customer.CustomerDetails.service.CustomerRepositery;




@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepositery repo;
	
	
	
	@GetMapping(value = "/customerDetails/{id}")
	public Customer getCustomerDetails(@PathVariable("id") Integer id) {
		Optional<Customer> optional= repo.findById(id);
		if(optional.isPresent()){
		Customer customer=  optional.get();
		return customer;
		}else {
			throw new RuntimeException("User Not found");
		}		
	}
}
