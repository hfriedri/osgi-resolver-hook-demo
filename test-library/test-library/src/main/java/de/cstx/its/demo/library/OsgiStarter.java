package de.cstx.its.demo.library;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.hooks.resolver.ResolverHookFactory;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;
import org.osgi.framework.wiring.FrameworkWiring;

public class OsgiStarter {

	
	public static void main(String[] args) throws BundleException {
	
		System.out.println("Begin....");
		
		Framework framework = initOsgiFramework();
		
		uninstallBundles(framework);
		
		registerResolverHook(framework);
		
		installBundles(framework);
		
		//refreshBundles(framework);
		
		startOsgiFramework(framework);
		
		startBundles(framework);
				
		stopOsgiFramework(framework);
		
		
		System.out.println("Ende....");
	}

	private static Framework initOsgiFramework() throws BundleException {
		ServiceLoader<FrameworkFactory> loader = ServiceLoader.load(FrameworkFactory.class);
		FrameworkFactory factory = loader.iterator().next();
		
		Map<String, String> configProperties = new HashMap<String, String>();
		//configProperties.put("osgi.noShutdown", "true");
		Framework framework = factory.newFramework(configProperties);
		
		framework.init();
		return framework;
	}

	private static void registerResolverHook(Framework framework) {
		framework.getBundleContext().registerService(ResolverHookFactory.class.getName(), new FiResolverHookFactory(), null);
	}

	private static void installBundles(Framework framework) {
		List<String> bundleFiles = listFilesUsingJavaIO("bundles\\plugins");
		for (String bundleFileName : bundleFiles) {
			Bundle bundle;
			try {
				bundle = framework.getBundleContext().installBundle("file:///" + bundleFileName);
				System.out.println("install bundle : " + bundle.getSymbolicName() + " ; Status : " + getStatus(bundle.getState()));
			} catch (BundleException e) {
				System.err.println("install bundle : " + bundleFileName + " failed!");
			}
		};
	}

	private static void startOsgiFramework(Framework framework) throws BundleException {
		System.out.println("Starte das OSGi-Framework");
		framework.start();
		System.out.println("Starten beendet.");
	}

	private static void stopOsgiFramework(Framework framework) throws BundleException {
		System.out.println("Stoppe das OSGi-Framework");
		framework.stop();
		System.out.println("Stoppen beendet.");
	}

	private static void refreshBundles(Framework framework) {
		System.out.println("Refresh Bundles");
		framework.getBundleContext().getBundle(Constants.SYSTEM_BUNDLE_LOCATION).adapt(FrameworkWiring.class).refreshBundles(Arrays.asList(framework.getBundleContext().getBundles()));
	}

	private static void startBundles(Framework framework) {
		for (Bundle bundle : framework.getBundleContext().getBundles()) {
			try {
				bundle.start();
				System.out.println("start bundle : " + bundle.getSymbolicName());
			} catch (BundleException e) {
				System.err.println("start bundle : " + bundle.getSymbolicName() + " failed! " + e.getMessage());
			}
		}
	}

	private static void uninstallBundles(Framework framework) {
		for (Bundle bundle : framework.getBundleContext().getBundles()) {
			try {
				bundle.uninstall();
				System.out.println("uninstall bundle : " + bundle.getSymbolicName() + " ; Status : " + getStatus(bundle.getState()));
			} catch (BundleException e) {
				System.err.println("uninstall bundle : " + bundle.getSymbolicName() + " failed!");
			}
		};
	}
	
	private static List<String> listFilesUsingJavaIO(String dir) {
	    return Stream.of(new File(dir).listFiles())
	      .filter(file -> !file.isDirectory())
	      .map(File::getAbsolutePath)
	      .collect(Collectors.toList());
	}
	
	private static String getStatus(int status) {
		if (status == Bundle.ACTIVE) {
			return "ACTIVE";
		} else if (status == Bundle.INSTALLED) {
			return "INSTALLED";
		} else if (status == Bundle.RESOLVED) {
			return "RESOLVED";
		} else if (status == Bundle.UNINSTALLED) {
			return "UNINSTALLED";
		} else if (status == Bundle.STARTING) {
			return "STARTING";
		}
		
		return "unknown";
	}

}
