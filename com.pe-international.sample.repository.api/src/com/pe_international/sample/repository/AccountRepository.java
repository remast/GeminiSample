/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package com.pe_international.sample.repository;

import java.util.Collection;

import model.account.Account;

public interface AccountRepository {

   Collection<Account> findAll();

   Account save(Account account);
   
   Account createTransient();

}
