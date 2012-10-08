/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package com.pe_international.sample.security.internal;

import javax.servlet.ServletException;

import org.eclipse.equinox.http.servlet.ExtendedHttpService;
import org.osgi.service.http.NamespaceException;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.util.Assert;

public class SecurityFilter {

   private static final String ERROR_MESSAGE_FILTER_INIT = "Couldn't initialize security filter.";

   private ExtendedHttpService httpService;

   private FilterChainProxy filterChainProxy;

   /**
    * Initialize filter for Spring security for all requests.
    */
   public void init() {
      Assert.notNull(httpService, "No HttpService present to register filter.");
      Assert.notNull(filterChainProxy, "No security filter present to register.");
      
      try {
         httpService.registerFilter("/", filterChainProxy, null, null);
      } catch (ServletException e) {
         throw new RuntimeException(ERROR_MESSAGE_FILTER_INIT, e); // NOPMD 
      } catch (NamespaceException e) {
         throw new RuntimeException(ERROR_MESSAGE_FILTER_INIT, e); // NOPMD 
      }
   }
   
   public void setHttpService(ExtendedHttpService httpService) {
      this.httpService = httpService;
   }

   public void setFilterChainProxy(FilterChainProxy filterChainProxy) {
      this.filterChainProxy = filterChainProxy;
   }

}
