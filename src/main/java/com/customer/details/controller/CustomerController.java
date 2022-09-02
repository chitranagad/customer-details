package com.customer.details.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.details.model.Customer;
import com.customer.details.model.CustomerRequest;
import com.customer.details.model.CustomerResponse;
import com.customer.details.service.CustomerService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping("/saveCustomer")
	//@Retry(name = "customer-detail", fallbackMethod  ="serviceDownResponse")
	@CircuitBreaker(name = "customer-detail", fallbackMethod  ="serviceDownResponse")
	public CustomerResponse saveCustomer(@RequestBody CustomerRequest request) {
		return service.saveCustomerRequest(request);
	}
	
	@PostMapping("/saveCustomerFeign")
	//@Retry(name = "customer-detail", fallbackMethod  ="serviceDownResponse")
	@CircuitBreaker(name = "customer-detail", fallbackMethod  ="serviceDownResponse")
	public CustomerResponse saveCustomerFeign(@RequestBody CustomerRequest request) {
		return service.saveCustomerRequestFeign(request);
	}

	@GetMapping(value = "/customerDetails/{id}")
	public Customer getCustomerDetails(@PathVariable("id") Integer id) {
		Optional<Customer> optional = service.findCustomerById(id);
		if (optional.isPresent()) {
			Customer customer = optional.get();
			return customer;
		} else {
			throw new RuntimeException("User Not found!!!!!");
		}
	}

	@GetMapping(value = "/allCustomerDetails")
	public List<Customer> getAllCustomerDetails() {
		return service.findAllCustomer();
	}
	
	public CustomerResponse serviceDownResponse(Exception ex) {
		
		return new CustomerResponse(new Customer(),"Opps try again...");
	}
}
