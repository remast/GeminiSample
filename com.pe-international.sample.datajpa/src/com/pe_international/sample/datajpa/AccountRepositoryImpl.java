package com.pe_international.sample.datajpa;

import org.springframework.beans.factory.annotation.Autowired;

public class AccountRepositoryImpl {

	@Autowired
	private AccountRepository accountRepository;
	

	public AccountRepository getAccountRepository() {
		return accountRepository;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

}
