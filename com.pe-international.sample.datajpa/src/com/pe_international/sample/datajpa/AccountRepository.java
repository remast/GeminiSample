package com.pe_international.sample.datajpa;

import java.util.List;

import model.account.Account;

public interface AccountRepository {
	
	public List<Account> findAll();
	
	public Account save(Account account);
	
}
