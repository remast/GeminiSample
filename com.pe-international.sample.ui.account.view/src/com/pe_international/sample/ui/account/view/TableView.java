/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package com.pe_international.sample.ui.account.view;

import model.account.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pe_international.sample.repository.AccountRepository;
import com.pe_international.sample.ui.main.service.ViewContribution;
import com.vaadin.Application;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class TableView implements ViewContribution {

   protected Logger logger = LoggerFactory.getLogger(TableView.class);

   private Component view;

   private Table table;

   @Override
   public String getIcon() {
      return "icons/money.png";
   }

   @Override
   public String getName() {
      return "Accounts";
   }

   private AccountRepository accountRepository;

   public AccountRepository getAccountRepository() {
      return accountRepository;
   }

   public void setAccountRepository(AccountRepository accountRepository) {
      this.accountRepository = accountRepository;
   }

   @Override
   public Component getView(Application application) {
      if (view == null) {
         final VerticalLayout verticalLayout = new VerticalLayout();
         verticalLayout.setMargin(true);
         verticalLayout.setSpacing(true);

         table = new Table();
         table.addContainerProperty("Customer", String.class, null);
         table.addContainerProperty("Balance", Double.class, null);
         table.setWidth("100%");
         table.setHeight("100%");
         // table.setPageLength(9);

         table.setImmediate(true);
         table.setColumnReorderingAllowed(true);
         table.setColumnCollapsingAllowed(true);
         
//         addButton = new Button("Add");
//         addButton.setIcon(new ThemeResource("icons/add.png"));
//         addButton.addListener(new Button.ClickListener() {
//
//            @Override
//            public void buttonClick(ClickEvent event) {
//               // Create the window...
//               final Window subwindow = new Window("Add Account");
//               // ...and make it modal
//               subwindow.setModal(true);
//               subwindow.setWidth("400px");
//               subwindow.setHeight("300px");
//
//               VerticalLayout layout = (VerticalLayout) subwindow.getContent();
//               layout.setMargin(true);
//               layout.setSpacing(true);
//               layout.setSizeFull();
//
//               Form form = new Form();
//
//               final Account account = accountRepository.createTransient();
//               account.setFirstName("Ford");
//               account.setLastName("Prefect");
//               account.setAddress("Earth");
//
//               BeanItem<Account> item = new BeanItem<Account>(account);
//
//               // Bind the bean item as the data source for the form.
//               form.setItemDataSource(item);
//
//
//               // Set the order of the items in the form.
//               Vector<String> order = new Vector<String>();
//               order.add("firstName");
//               order.add("lastName");
//               order.add("address");
//               form.setVisibleItemProperties(order);
//               // form.setCaption("Contact Information");
//               form.setDescription("Please specify the details of the account.");
//
//               layout.addComponent(form);
//
//               Button save = new Button("Save", new Button.ClickListener() {
//                  // inline click-listener
//                  public void buttonClick(ClickEvent event) {
//                     accountRepository.save(account);
//                     // close the window by removing it from the parent window
//                     subwindow.getParent().removeWindow(subwindow);
//                     refreshTable();
//                  }
//               });
//               save.setIcon(new ThemeResource("icons/bullet_disk.png"));
//
//               // The components added to the window are actually added to the window's
//               // layout; you can use either. Alignments are set using the layout
//               layout.addComponent(save);
//               layout.setComponentAlignment(save, Alignment.BOTTOM_RIGHT);
//
//               verticalLayout.getWindow().addWindow(subwindow);
//            }
//         });
//         verticalLayout.addComponent(addButton);
         
         long countAccounts = accountRepository.count();
         Label l = new Label(String.format("Found %s accounts.",countAccounts));
         verticalLayout.addComponent(l);

         verticalLayout.addComponent(table);
         view = verticalLayout;

         refreshTable();

      }
      return view;
   }

   void refreshTable() {
      synchronized (this) {
         if (accountRepository != null) {
            table.removeAllItems();

            int i = 1;
            for (Account person : accountRepository.findAll()) {
               String customerName = person.getCustomer().getLastName() + ", " + person.getCustomer().getFirstName();
               Double balance = person.getBalance();
               table.addItem(new Object[] { customerName, balance }, i++);
            }
         }
      }
   }

}