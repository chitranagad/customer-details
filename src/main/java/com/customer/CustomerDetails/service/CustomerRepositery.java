package com.customer.CustomerDetails.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.CustomerDetails.model.Customer;

@Repository
public interface CustomerRepositery extends JpaRepository<Customer, Integer> {

}
