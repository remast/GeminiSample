/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package model.account.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.account.Account;
import model.account.Customer;

/**
 * Sample JPA model class
 * 
 * @author mkeith
 */
@Entity
public class AccountImpl implements Account {

    @Id @GeneratedValue
    Integer id;
    
    double balance;
    
    @OneToOne(mappedBy="account",targetEntity=CustomerImpl.class)
    Customer customer;

    @Temporal(TemporalType.DATE)
    public Date dateCreated;

    /* Constructors */
    public AccountImpl() { 
        dateCreated = new Date(System.currentTimeMillis()); 
    }
    public AccountImpl(Customer customer) {
        this();
        this.balance = 0;
        this.customer = customer;
        customer.setAccount(this);
    }
    
    /* Getters and setters */
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    
    public String toString() {
        return "AccountImpl(" + id + ", " + ((customer!=null)?customer.getLastName():"null") + ", Balance: $" + balance + ")";
    }
}
