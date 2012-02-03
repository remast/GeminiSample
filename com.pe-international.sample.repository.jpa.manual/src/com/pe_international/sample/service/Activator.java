/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package com.pe_international.sample.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

   private static BundleContext context;

   static BundleContext getContext() {
      return context;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
    */
   public void start(BundleContext bundleContext) throws Exception {
      Activator.context = bundleContext;
   }

   /*
    * (non-Javadoc)
    * 
    * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
    */
   public void stop(BundleContext bundleContext) throws Exception {
      Activator.context = null;
   }

}
