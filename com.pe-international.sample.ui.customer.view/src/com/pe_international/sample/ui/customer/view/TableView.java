/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package com.pe_international.sample.ui.customer.view;

import java.util.Vector;

import model.account.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pe_international.sample.repository.CustomerRepository;
import com.pe_international.sample.ui.main.service.ViewContribution;
import com.vaadin.Application;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Form;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class TableView implements ViewContribution {

   protected Logger logger = LoggerFactory.getLogger(TableView.class);

   private Component view;

   private Table table;

   private Button addButton;

   @Override
   public String getIcon() {
      return "icons/group.png";
   }

   @Override
   public String getName() {
      return "Customers";
   }

   private CustomerRepository customerRepository;

   public CustomerRepository getCustomerRepository() {
      return customerRepository;
   }

   public void setCustomerRepository(CustomerRepository customerRepository) {
      this.customerRepository = customerRepository;
   }

   @Override
   public Component getView(Application application) {
      if (view == null) {
         final VerticalLayout verticalLayout = new VerticalLayout();
         verticalLayout.setMargin(true);
         verticalLayout.setSpacing(true);

         table = new Table();
         table.addContainerProperty("Name", String.class, null);
         table.addContainerProperty("First Name", String.class, null);
         table.addContainerProperty("Last Name", String.class, null);
         table.addContainerProperty("Adress", String.class, null);
         table.setWidth("100%");
         table.setHeight("100%");
         // table.setPageLength(9);

         table.setImmediate(true);
         table.setColumnReorderingAllowed(true);
         table.setColumnCollapsingAllowed(true);

         addButton = new Button("Add");
         addButton.setIcon(new ThemeResource("icons/user_add.png"));
         addButton.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
               // Create the window...
               final Window subwindow = new Window("Add Customer");
               // ...and make it modal
               subwindow.setModal(true);
               subwindow.setWidth("400px");
               subwindow.setHeight("300px");

               VerticalLayout layout = (VerticalLayout) subwindow.getContent();
               layout.setMargin(true);
               layout.setSpacing(true);
               layout.setSizeFull();

               Form form = new Form();

               final Customer customer = customerRepository.createTransient();
               customer.setFirstName("Ford");
               customer.setLastName("Prefect");
               customer.setAddress("Earth");

               BeanItem<Customer> item = new BeanItem<Customer>(customer);

               // Bind the bean item as the data source for the form.
               form.setItemDataSource(item);


               // Set the order of the items in the form.
               Vector<String> order = new Vector<String>();
               order.add("firstName");
               order.add("lastName");
               order.add("address");
               form.setVisibleItemProperties(order);
               // form.setCaption("Contact Information");
               form.setDescription("Please specify the details of the customer.");

               layout.addComponent(form);

               Button save = new Button("Save", new Button.ClickListener() {
                  // inline click-listener
                  public void buttonClick(ClickEvent event) {
                     customerRepository.save(customer);
                     // close the window by removing it from the parent window
                     subwindow.getParent().removeWindow(subwindow);
                     refreshTable();
                  }
               });
               save.setIcon(new ThemeResource("icons/bullet_disk.png"));

               // The components added to the window are actually added to the window's
               // layout; you can use either. Alignments are set using the layout
               layout.addComponent(save);
               layout.setComponentAlignment(save, Alignment.BOTTOM_RIGHT);

               verticalLayout.getWindow().addWindow(subwindow);
            }
         });
         verticalLayout.addComponent(addButton);

         verticalLayout.addComponent(table);
         view = verticalLayout;

         refreshTable();

      }
      return view;
   }

   void refreshTable() {
      synchronized (this) {
         if (customerRepository != null) {
            table.removeAllItems();

            int i = 1;
            for (Customer person : customerRepository.findAll()) {
               String firstName = person.getFirstName();
               String lastName = person.getLastName();
               String address = person.getAddress();
               table.addItem(new Object[] { firstName + " " + lastName, firstName, lastName, address }, i++);
            }
         }
      }
   }

}