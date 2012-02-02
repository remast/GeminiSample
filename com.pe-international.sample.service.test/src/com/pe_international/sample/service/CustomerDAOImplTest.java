/**
 * 
 */
package com.pe_international.sample.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/bundle-context-test.xml"})
public class CustomerDAOImplTest {
	
	@Autowired
	CustomerDAO customerDAO;

	@Test
	public void testAddCusotmer() {
		assertEquals(0, customerDAO.list().size());
		
		String firstName = "First";
		String lastName = "Last";
		String address = "Address";
		customerDAO.addCustomer(lastName, firstName, address);
		
		assertEquals(1, customerDAO.list().size());
	}

}
