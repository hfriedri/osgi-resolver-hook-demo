package de.cstx.its.demo.library;

import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

public class Listener implements BundleListener {

	@Override
	public void bundleChanged(BundleEvent event) {
		
		System.out.println("BundleChanged : " + event.getBundle().getSymbolicName() + ", EventType : " + getStatus(event.getType()));

	}
	
	private static String getStatus(int status) {
		if (status == BundleEvent.INSTALLED) {
			return "INSTALLED";
		} else if (status == BundleEvent.RESOLVED) {
			return "RESOLVED";
		} else if (status == BundleEvent.UNINSTALLED) {
			return "UNINSTALLED";
		} else if (status == BundleEvent.STARTING) {
			return "STARTING";
		} else if (status == BundleEvent.UPDATED) {
			return "UPDATED";
		
	} else if (status == BundleEvent.STARTED) {
		return "STARTED";
	}
		
		return "unknown";
	}

}
