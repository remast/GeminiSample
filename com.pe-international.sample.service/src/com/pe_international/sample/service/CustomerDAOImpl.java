package com.pe_international.sample.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.account.Customer;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public Collection<Customer> list() {
		TypedQuery<Customer> q = entityManager.createQuery("SELECT a FROM Customer a", Customer.class);
		List<Customer> results = q.getResultList();

		return results;
	}

	@Transactional
	public void addCustomer(String lastName, String firstName, String address) {
		Customer c = new Customer(lastName, firstName, address);
		entityManager.persist(c);
	}

}
