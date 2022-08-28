package com.customer.customer.details.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.customer.details.model.Customer;
import com.customer.customer.details.model.CustomerRequest;
import com.customer.customer.details.model.CustomerResponse;
import com.customer.customer.details.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping("/saveCustomer")
	public CustomerResponse saveCustomer(@RequestBody CustomerRequest request) {
		return service.saveCustomerRequest(request);
	}

	@GetMapping(value = "/customerDetails/{id}")
	public Customer getCustomerDetails(@PathVariable("id") Integer id) {
		Optional<Customer> optional = service.findCustomerById(id);
		if (optional.isPresent()) {
			Customer customer = optional.get();
			return customer;
		} else {
			throw new RuntimeException("User Not found");
		}
	}

	@GetMapping(value = "/allCustomerDetails")
	public List<Customer> getAllCustomerDetails() {
		return service.findAllCustomer();
	}
}
