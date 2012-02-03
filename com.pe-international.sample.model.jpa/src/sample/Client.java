/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package sample;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import model.account.jpa.AccountImpl;
import model.account.jpa.CustomerImpl;

/**
 * Gemini JPA sample client class
 */
public class Client {

   public void run(EntityManagerFactory emf) {
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();

      CustomerImpl customer1 = new CustomerImpl("Chan", "Jackie", "1034 KingFu Lane, Los Angeles, CA");
      em.persist(customer1);
      
      AccountImpl account1 = new AccountImpl(customer1);
      account1.setBalance(100.0);
      em.persist(account1);
      
      AccountImpl account2 = new AccountImpl(customer1);
      account2.setBalance(42.0);
      em.persist(account2);

      em.getTransaction().commit();

      TypedQuery<AccountImpl> q = em.createQuery("SELECT a FROM AccountImpl a", AccountImpl.class);
      List<AccountImpl> results = q.getResultList();
      System.out.println("\n*** AccountImpl Report ***");
      for (AccountImpl acct : results) {
         System.out.println("AccountImpl: " + acct);
      }
      em.close();
   }
}