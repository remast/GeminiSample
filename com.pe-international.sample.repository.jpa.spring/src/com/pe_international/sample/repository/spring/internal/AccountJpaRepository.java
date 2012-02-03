/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package com.pe_international.sample.repository.spring.internal;

import java.util.List;

import model.account.jpa.AccountImpl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountImpl, Integer> {
	
	@Override
	public List<AccountImpl> findAll();
	
	@Override
	public AccountImpl save(AccountImpl account);

}
