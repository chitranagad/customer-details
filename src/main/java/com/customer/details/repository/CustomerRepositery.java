package com.customer.details.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.details.model.Customer;

@Repository
public interface CustomerRepositery extends JpaRepository<Customer, Integer> {

}
