/*******************************************************************************
 * Copyright (c) 2010 Kai Toedter and Siemens AG
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Kai Toedter - initial API and implementation
 *******************************************************************************/

package com.pe_international.vaadin.pm.tableview;

import model.account.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pe_international.sample.service.CustomerDAO;
import com.pe_international.vaadin.pm.main.service.IViewContribution;
import com.vaadin.Application;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class TableView implements IViewContribution {

	protected Logger logger = LoggerFactory.getLogger(TableView.class);

	private Component view;

	private Table table;

	@Override
	public String getIcon() {
		return "icons/application_view_columns.png";
	}

	@Override
	public String getName() {
		return "Table View";
	}
	
	private CustomerDAO customerDAO;

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	public Component getView(Application application) {
		if (view == null) {
			VerticalLayout verticalLayout = new VerticalLayout();
			verticalLayout.setMargin(true);

			table = new Table();
			table.addContainerProperty("Name", String.class, null);
			table.addContainerProperty("First Name", String.class, null);
			table.addContainerProperty("Last Name", String.class, null);
			table.addContainerProperty("Adress", String.class, null);
			table.setWidth("100%");
			table.setPageLength(9);

			table.setImmediate(true);

			verticalLayout.addComponent(table);
			view = verticalLayout;

//			synchronized (this) {
//				if (personManager != null) {
					refreshTable();
//				}
//			}

		}
		return view;
	}

//	public void removePersonManager(final IPersonManager personManager) {
//		logger.debug("TableView.removePersonManager()");
//		if (this.personManager == personManager) {
//			this.personManager = null;
//			table.removeAllItems();
//		}
//	}

//	public void setPersonManager(final IPersonManager personManager) {
//		logger.debug("TableView.setPersonManager()");
////		synchronized (this) {
////			this.personManager = personManager;
////			if (table != null) {
//				refreshTable();
////			}
////		}
//	}

	void refreshTable() {
//		List<IPerson> persons = personManager.getPersons();
		table.removeAllItems();

		int i = 1;
		for (Customer person : customerDAO.list()) {
//		for (int j = 0; j < 20; j++) {
			
			String firstName =  person.getFirstName();
			String lastName = person.getLastName();
			String address = person.getAddress();
			table.addItem(
			      new Object[] {
//			            person.getFirstName() + " " + person.getLastName(),
//			            person.getFirstName(), person.getLastName(),
//			            person.getCompany() }, i++);
			    		  firstName + " " + lastName,
			    		  firstName, lastName,
			    		  address }, i++);
		}
	}
}
