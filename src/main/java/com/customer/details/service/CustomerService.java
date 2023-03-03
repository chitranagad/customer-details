package com.customer.details.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.customer.details.model.Customer;
import com.customer.details.model.CustomerRequest;
import com.customer.details.model.CustomerResponse;
import com.customer.details.model.Payment;
import com.customer.details.repository.CustomerRepositery;
import com.customer.details.service.proxy.CustomerPaymentProxy;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepositery repositery;
	@Autowired
	private RestTemplate template;
	@Autowired
	private CustomerPaymentProxy proxy;
	@Value("${microservice.payment-service.endpoints.endpoint.uri}")
	private String PAYMENT_URI;
	@Autowired
	private KafkaTemplate<String, Payment> kafkatemplate;
	@Value("${spring.kafka.producer.topic}")
	private String topic;

	@Transactional
	public CustomerResponse saveCustomerRequest(CustomerRequest request) {
		String paymentStatus = "";
		Customer customer = request.getCustomer();
		Payment payment = request.getPayment();
		customer = repositery.save(customer);
		payment.setCustomerId(customer.getId());
		Payment paymentResp = template.postForObject(PAYMENT_URI, payment, Payment.class);
		paymentStatus = paymentResp.getStatus().equals("success") ? "payment processing successfully order placed"
				: "Order added in cart";
		return new CustomerResponse(customer, paymentStatus);
	}

	@Transactional
	public CustomerResponse saveCustomerRequestFeign(CustomerRequest request) {
		String paymentStatus = "";
		Customer customer = request.getCustomer();
		Payment payment = request.getPayment();
		customer = repositery.save(customer);
		payment.setCustomerId(customer.getId());
		Payment paymentResp = proxy.processPayment(payment);
		paymentStatus = paymentResp.getStatus().equals("success") ? "payment processing successfully order placed"
				: "Order added in cart";
		return new CustomerResponse(customer, paymentStatus);
	}
	
	public CustomerResponse sendCustomerRequestToKafkaBroker(CustomerRequest request) {
		Customer customer = request.getCustomer();
		Payment payment = request.getPayment();
		customer = repositery.save(customer);
		payment.setCustomerId(customer.getId());
		kafkatemplate.send(topic, payment);
		
		return new CustomerResponse(customer, "payment process by kafka broker");
	}
	
	public Optional<Customer> findCustomerById(Integer id) {
		return repositery.findById(id);
	}

	public List<Customer> findAllCustomer() {
		return repositery.findAll();
	}

	
}
