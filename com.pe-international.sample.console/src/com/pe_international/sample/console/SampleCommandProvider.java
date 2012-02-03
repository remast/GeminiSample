/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package com.pe_international.sample.console;

import java.util.Collection;

import model.account.Account;
import model.account.Customer;

import org.eclipse.osgi.framework.console.CommandInterpreter;
import org.eclipse.osgi.framework.console.CommandProvider;

import com.pe_international.sample.repository.AccountRepository;
import com.pe_international.sample.repository.CustomerRepository;


public class SampleCommandProvider implements CommandProvider {

   private AccountRepository accountRepository;

   public AccountRepository getAccountRepository() {
      return accountRepository;
   }

   public void setAccountRepository(AccountRepository accountRepository) {
      this.accountRepository = accountRepository;
   }

   public void _listAccounts(final CommandInterpreter interpreter) {
      System.out.println("------- Accounts: -------");
      Collection<Account> accounts = accountRepository.findAll();
      for (Account account : accounts) {
         System.out.println(account);
      }
      System.out.println("-------------------------");
   }

   public void _listCustomers(final CommandInterpreter interpreter) {
      System.out.println("------- Customers: -------");
      Collection<Customer> customers = customerRepository.findAll();
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
         customerRepository.addCustomer(lastName, firstName, address);
      }
      System.out.println("------- Customers added ----------------------");
   }

   private CustomerRepository customerRepository;

   public CustomerRepository getCustomerRepository() {
      return customerRepository;
   }

   public void setCustomerRepository(CustomerRepository customerRepository) {
      this.customerRepository = customerRepository;
   }

   @Override
   public String getHelp() {
      return "SampleCommandProvider Help";
   }

}
