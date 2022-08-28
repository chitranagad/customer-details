package com.customer.customer.details.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.customer.customer.details.model.Customer;
import com.customer.customer.details.model.CustomerRequest;
import com.customer.customer.details.model.CustomerResponse;
import com.customer.customer.details.model.Payment;
import com.customer.customer.details.repository.CustomerRepositery;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepositery repositery;
	@Autowired
	private RestTemplate template;
    
	@Transactional
	public CustomerResponse saveCustomerRequest(CustomerRequest request) {
		String paymentStatus = "";
		Customer customer = request.getCustomer();
		Payment payment = request.getPayment();
		customer= repositery.save(customer);
		payment.setCustomerId(customer.getId());
		Payment paymentResp = template.postForObject("http://CUSTOMER-PAYMENT/payment/processPay", payment, Payment.class);
		paymentStatus= paymentResp.getStatus().equals("success")?"payment processing successfully order placed":"Order added in cart";
		return new CustomerResponse(customer, paymentStatus);	 
	}

	public Optional<Customer> findCustomerById(Integer id) {
		return repositery.findById(id);
	}

	public List<Customer> findAllCustomer() {
		return repositery.findAll();
	}
}
