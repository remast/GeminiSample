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

import javax.persistence.EntityManagerFactory;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.jpa.EntityManagerFactoryBuilder;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * Gemini JPA sample activator class
 * 
 * @author mkeith
 */
public class Activator implements BundleActivator, ServiceTrackerCustomizer {

    BundleContext ctx;
    ServiceTracker emfTracker;
    Client client;

    public void start(BundleContext context) throws Exception {
        ctx = context;
        client = new Client();
        System.out.println("Gemini JPA Sample started");

        /* We are in the same bundle as the persistence unit so the services should be 
         * available when we start up (if nothing bad happened) and the tracker is really 
         * just saving us the lookup, but this is the idea of how you would listen for a 
         * persistence unit coming from another bundle.
         */
        emfTracker = new ServiceTracker(ctx, EntityManagerFactory.class.getName(), this);
        emfTracker.open();
    }

    public void stop(BundleContext context) throws Exception {
        emfTracker.close();
        client = null;
        System.out.println("Gemini JPA Sample stopped");
    }
    
    /*========================*/
    /* ServiceTracker methods */
    /*========================*/

    public Object addingService(ServiceReference ref) {
        Bundle b = ref.getBundle();
        Object service = b.getBundleContext().getService(ref);
        String unitName = (String)ref.getProperty(EntityManagerFactoryBuilder.JPA_UNIT_NAME);
        if (unitName.equals("Accounts")) {
            client.run((EntityManagerFactory)service);
        }
        return service;
    }
    public void modifiedService(ServiceReference ref, Object service) {}
    public void removedService(ServiceReference ref, Object service) {}    
}