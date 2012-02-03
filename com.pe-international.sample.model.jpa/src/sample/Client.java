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

      CustomerImpl c = new CustomerImpl("Chan", "Jackie", "1034 KingFu Lane, Los Angeles, CA");
      em.persist(c);
      AccountImpl a = new AccountImpl(c);
      a.setBalance(100.0);
      em.persist(a);

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