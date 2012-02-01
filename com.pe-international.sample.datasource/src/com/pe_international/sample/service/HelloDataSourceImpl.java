package com.pe_international.sample.service;

import javax.sql.DataSource;

public class HelloDataSourceImpl implements HelloDataSource {
	
	private DataSource dataSource;

	public void sayHello() {
		System.out.println("Hello Data Source");
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
