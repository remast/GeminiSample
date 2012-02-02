package com.pe_international.sample.datajpa;

import java.util.ArrayList;
import java.util.List;

import model.account.Account;
import model.account.jpa.AccountImpl;

import com.pe_international.sample.datajpa.internal.AccountJpaRepository;

public class AccountRepositoryImpl implements AccountRepository {
	
	private AccountJpaRepository accountJpaRepository;

	public void setAccountJpaRepository(AccountJpaRepository accountJpaRepository) {
		this.accountJpaRepository = accountJpaRepository;
	}

	@Override
	public List<Account> findAll() {
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

}
