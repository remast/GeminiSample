/*******************************************************************************
 * Copyright (c) 2010 Oracle.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution. 
 * The Eclipse Public License is available at
 *     http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at 
 *     http://www.opensource.org/licenses/apache2.0.php.
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *     mkeith - Gemini JPA Sample 
 ******************************************************************************/
package model.account;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Sample JPA model class
 * 
 * @author mkeith
 */
@Entity
public class Account {

    @Id @GeneratedValue
    Integer id;
    
    double balance;
    
    @OneToOne(mappedBy="account")
    Customer customer;

    @Temporal(TemporalType.DATE)
    public Date dateCreated;

    /* Constructors */
    public Account() { 
        dateCreated = new Date(System.currentTimeMillis()); 
    }
    public Account(Customer customer) {
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
        return "Account(" + id + ", " + ((customer!=null)?customer.getLastName():"null") + ", Balance: $" + balance + ")";
    }
}
