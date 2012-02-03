/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package com.pe_international.sample.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.account.Customer;
import model.account.jpa.CustomerImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pe_international.sample.repository.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

   private EntityManager entityManager;

   @PersistenceContext
   public void setEntityManager(EntityManager entityManager) {
      this.entityManager = entityManager;
   }

   @Transactional
   public Collection<Customer> findAll() {
      TypedQuery<CustomerImpl> q = entityManager.createQuery("SELECT a FROM CustomerImpl a", CustomerImpl.class);
      List<CustomerImpl> results = q.getResultList();

      List<Customer> as = new ArrayList<Customer>();
      for (Customer a : results) {
         as.add(a);
      }

      return as;
   }

   @Transactional
   public void addCustomer(String lastName, String firstName, String address) {
      CustomerImpl c = new CustomerImpl(lastName, firstName, address);
      entityManager.persist(c);
   }

   @Override
   public Customer createTransient() {
      return new CustomerImpl();
   }

   @Override
   @Transactional
   public Customer save(Customer customer) {
      CustomerImpl customerImpl = (CustomerImpl) customer;
      entityManager.persist(customerImpl);
      return customerImpl;
   }

}
