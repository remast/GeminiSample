package com.pe_international.sample.console;

import java.util.Collection;

import model.account.Customer;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import com.pe_international.sample.service.CustomerDAO;


public class SampleCommandProvider implements CommandProvider {

	public void _listCustomers(final CommandInterpreter interpreter) {
		System.out.println("------- Customers: -------");
		Collection<Customer> customers =  customerDAO.list();
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		System.out.println("---------------------------");
	}

	public void _addCustomers(final CommandInterpreter interpreter) {
		int count = 10;
		System.out.println("------- Adding " + count + " Customers -------");
		for (int i = 0; i < count; i++) {
			String firstName = "First Name " + i;
			String lastName = "Last Name " + i;
			String address = "Adress " + i;
			customerDAO.addCustomer(lastName, firstName, address);
		}
		System.out.println("------- Customers added ----------------------");
	}

	private CustomerDAO customerDAO;

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	public String getHelp() {
		return "SampleCommandProvider Help";
	}

}
