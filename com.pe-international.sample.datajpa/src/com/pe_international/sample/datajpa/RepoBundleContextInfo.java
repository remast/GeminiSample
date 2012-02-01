package com.pe_international.sample.datajpa;

import org.eclipse.gemini.blueprint.context.BundleContextAware;
import org.osgi.framework.BundleContext;

public class RepoBundleContextInfo implements BundleContextAware {

	private BundleContext context;

	@Override
	public void setBundleContext(BundleContext context) {
		this.context = context;
	}

}
