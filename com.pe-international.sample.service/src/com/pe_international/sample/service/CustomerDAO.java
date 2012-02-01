package com.pe_international.sample.service;

import java.util.Collection;

import model.account.Customer;

public interface CustomerDAO {
	
	Collection<Customer> list(); 
	
	void addCustomer(String lastName, String firstName, String address);

}
