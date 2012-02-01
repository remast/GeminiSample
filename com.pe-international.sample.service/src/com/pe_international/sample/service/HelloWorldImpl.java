package com.pe_international.sample.service;

import javax.persistence.EntityManagerFactory;

import org.springframework.orm.jpa.JpaTransactionManager;

public class HelloWorldImpl implements HelloWorld {
	
	private EntityManagerFactory entityManagerFactory;
	
	private JpaTransactionManager transactionManager;

	public JpaTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(JpaTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public void sayHello() {
		System.out.println("Hello World");
	}

}
