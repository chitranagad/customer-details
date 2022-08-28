package com.customer.customer.details.model;

public class CustomerResponse {

	private Customer customer;
	private String paymentResponse;
    
	
	public CustomerResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerResponse(Customer customer, String paymentResponse) {
		super();
		this.customer = customer;
		this.paymentResponse = paymentResponse;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPaymentResponse() {
		return paymentResponse;
	}

	public void setPaymentResponse(String paymentResponse) {
		this.paymentResponse = paymentResponse;
	}

}
