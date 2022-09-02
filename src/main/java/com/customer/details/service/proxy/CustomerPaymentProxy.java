package com.customer.details.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.customer.details.model.Payment;

@FeignClient(name = "CUSTOMER-PAYMENT", url = "http://localhost:9091/payment")
public interface CustomerPaymentProxy {

	@PostMapping("/processPay")
	public Payment processPayment(Payment payment);
}
