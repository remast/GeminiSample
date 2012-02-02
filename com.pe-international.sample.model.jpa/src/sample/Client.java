/*******************************************************************************
 * Copyright (c) 2010 Oracle.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution. 
 * The Eclipse Public License is available at
 *     http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at 
 *     http://www.opensource.org/licenses/apache2.0.php.
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *     mkeith - Gemini JPA sample 
 ******************************************************************************/
package sample;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import model.account.jpa.AccountImpl;
import model.account.jpa.CustomerImpl;

/**
 * Gemini JPA sample client class
 * 
 * @author mkeith
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