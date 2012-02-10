/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package model.account.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import model.account.Account;
import model.account.Customer;

/**
 * Gemini JPA Sample class
 */
@Entity
public class CustomerImpl implements Customer {
	
   @Id
   @GeneratedValue
   int id;

   @Column(name = "LNAME")
   String lastName;

   @Column(name = "FNAME")
   String firstName;

   @Column(name = "ADDR")
   String address;

   @OneToOne(targetEntity = AccountImpl.class)
   Account account;

   /* Constructors */
   public CustomerImpl() {
      super();
   }

   public CustomerImpl(String lastName, String firstName, String address) {
      super();
      this.lastName = lastName;
      this.firstName = firstName;
      this.address = address;
   }

   /* Getters and setters */
   public int getId() {
      return id;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public Account getAccount() {
      return account;
   }

   public void setAccount(Account account) {
      this.account = account;
   }

   public String toString() {
      return "CustomerImpl(" + firstName + " " + lastName + ", " + address + ")";
   }
}
