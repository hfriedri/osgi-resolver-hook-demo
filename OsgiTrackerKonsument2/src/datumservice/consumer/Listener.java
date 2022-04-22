package datumservice.consumer;

import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

public class Listener implements BundleListener {

	@Override
	public void bundleChanged(BundleEvent event) {
		
		System.out.println("BundleChanged : " + event.getBundle().getSymbolicName() + ", EventType : " + event.getType());

	}

}
