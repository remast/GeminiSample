package com.pe_international.sample.repository;

import java.util.Collection;

import model.account.Account;

public interface AccountRepository {
	
	public Collection<Account> findAll();
	
	public Account save(Account account);
	
}
