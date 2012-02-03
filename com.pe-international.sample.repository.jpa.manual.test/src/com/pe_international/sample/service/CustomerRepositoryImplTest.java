/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package com.pe_international.sample.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pe_international.sample.repository.CustomerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/bundle-context-test.xml"})
public class CustomerRepositoryImplTest {
	
	@Autowired
	CustomerRepository customerRepository;

	@Test
	public void testAddCustomer() {
		assertEquals(0, customerRepository.findAll().size());
		
		String firstName = "First";
		String lastName = "Last";
		String address = "Address";
		customerRepository.addCustomer(lastName, firstName, address);
		
		assertEquals(1, customerRepository.findAll().size());
	}

}
