package com.pe_international.sample.service;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import model.account.Customer;

import org.springframework.transaction.annotation.Transactional;

public class CustomerDAOImpl implements CustomerDAO {

	private EntityManagerFactory entityManagerFactory;

	//	private JpaTransactionManager transactionManager;
	//
	//	public JpaTransactionManager getTransactionManager() {
	//		return transactionManager;
	//	}
	//
	//	public void setTransactionManager(JpaTransactionManager transactionManager) {
	//		this.transactionManager = transactionManager;
	//	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}


	@Transactional
	public Collection<Customer> list() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		TypedQuery<Customer> q = entityManager.createQuery("SELECT a FROM Customer a", Customer.class);
		List<Customer> results = q.getResultList();

		return results;
	}

	@Transactional
	public void addCustomer(String lastName, String firstName, String address) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		try {
			Customer c = new Customer(lastName, firstName, address);
			entityManager.persist(c);
		} finally {
			entityManager.close();
		}

	}

}
