/*******************************************************************************
 * Copyright (c) 2012 PE INTERNATIONAL AG.
 * All rights reserved.
 * 
 * Contributors:
 *    Jan Stamer - initial API and implementation
 *******************************************************************************/
package com.pe_international.sample.ui.main.service;

import com.vaadin.Application;

public interface ActionContribution {
	String getIcon();

	String getText();

	void execute(Application application);
}
