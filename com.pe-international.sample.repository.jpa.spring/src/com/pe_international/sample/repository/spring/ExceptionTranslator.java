package com.pe_international.sample.repository.spring;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;

public class ExceptionTranslator implements PersistenceExceptionTranslator {
	
	private JpaTransactionManager transactionManager;

	@Override
	public DataAccessException translateExceptionIfPossible(
			RuntimeException e) {
		if (transactionManager != null) {
			return transactionManager.getJpaDialect().translateExceptionIfPossible(e);
		}
		
		// TODO Auto-generated method stub
		return null;
	}

	public JpaTransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(JpaTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
