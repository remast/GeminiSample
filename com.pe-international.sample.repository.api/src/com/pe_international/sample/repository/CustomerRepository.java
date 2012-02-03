package com.pe_international.sample.repository;

import java.util.Collection;

import model.account.Customer;

public interface CustomerRepository {
	
	Collection<Customer> findAll(); 
	
	void addCustomer(String lastName, String firstName, String address);
	
	Customer createTransient();
	
	Customer save(Customer customer);

}
