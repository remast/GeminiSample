/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package com.pe_international.sample.ui.main.service;

import com.vaadin.Application;
import com.vaadin.ui.Component;

/**
 * A simple view UI contribution
 */
public interface ViewContribution {
	public Component getView(Application application);

	public String getIcon();

	public String getName();
}
