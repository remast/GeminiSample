package com.pe_international.sample.datajpa;

import java.util.List;

import model.account.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	@Override
	public List<Account> findAll();
	
	@Override
	public Account save(Account account);

}
