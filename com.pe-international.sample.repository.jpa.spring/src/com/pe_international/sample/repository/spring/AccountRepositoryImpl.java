/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package com.pe_international.sample.repository.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import model.account.Account;
import model.account.jpa.AccountImpl;

import com.pe_international.sample.repository.AccountRepository;
import com.pe_international.sample.repository.spring.internal.AccountJpaRepository;

public class AccountRepositoryImpl implements AccountRepository {

   private AccountJpaRepository accountJpaRepository;

   public void setAccountJpaRepository(AccountJpaRepository accountJpaRepository) {
      this.accountJpaRepository = accountJpaRepository;
   }

   @Override
   public Collection<Account> findAll() {
      List<Account> as = new ArrayList<Account>();
      for (Account a : accountJpaRepository.findAll()) {
         as.add(a);
      }
      return as;
   }

   @Override
   public Account save(Account account) {
      return accountJpaRepository.save((AccountImpl) account);
   }

   @Override
   public Account createTransient() {
      return new AccountImpl();
   }

   @Override
   public long count() {
      return accountJpaRepository.count();
   }

}
