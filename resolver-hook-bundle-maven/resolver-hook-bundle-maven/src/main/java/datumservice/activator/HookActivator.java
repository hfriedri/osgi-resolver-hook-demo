package datumservice.activator;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.hooks.resolver.ResolverHookFactory;

import datumservice.hooks.OsgiResolverHookFactory;

public class HookActivator implements BundleActivator {

	/**
	 * 
	 * Implements BundleActivator.start().
	 * 
	 * @param bundleContext - the framework context for the bundle.
	 **/

	public void start(BundleContext bundleContext) {
		System.out.println("Start ResolverHook Activator");
		
		bundleContext.registerService(ResolverHookFactory.class.getName(), new OsgiResolverHookFactory(), null);
	}

	/**
	 * 
	 * Implements BundleActivator.stop()
	 * 
	 * @param bundleContext - the framework context for the bundle
	 **/
	public void stop(BundleContext bundleContext) {
		System.out.println("Goodbye REsolverHook Activator");
	}
}