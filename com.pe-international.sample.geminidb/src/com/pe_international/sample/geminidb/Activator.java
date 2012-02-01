package com.pe_international.sample.geminidb;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.jdbc.DataSourceFactory;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class Activator implements BundleActivator, ServiceTrackerCustomizer {

	ServiceTracker dsfTracker;

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;

		dsfTracker = new ServiceTracker(context, DataSourceFactory.class.getName(), this);
		dsfTracker.open();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

	/* === ServiceTracker methods === */

	public Object addingService(ServiceReference ref) {
		Object service = context.getService(ref);

		//        String driver = (String)ref.getProperty(DataSourceFactory.OSGI_JDBC_DRIVER_CLASS);
		//        String version = (String)ref.getProperty(DataSourceFactory.OSGI_JDBC_DRIVER_VERSION);
		//
		//        if (driver != null && driver.equalsIgnoreCase(EMBEDDED_DERBY_DRIVER_NAME) &&
		//                version != null && version.equalsIgnoreCase(JDBC_4_VERSION)) {
		//            log("Sample Gemini DBAccess client notified of service: " + driver);
		//
		//            // We have a JDBC service, now do something with it
		//            DataSourceFactory dsf = (DataSourceFactory) service;
		//            useEmbeddedDataSource(dsf);
		//        }
		return service;
	}

	public void modifiedService(ServiceReference ref, Object service) { /* Do nothing */ }

	public void removedService(ServiceReference ref, Object service) { context.ungetService(ref); }

}
