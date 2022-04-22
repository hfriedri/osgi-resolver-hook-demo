package de.cstx.its.demo.library;

import java.util.Collection;
import java.util.Dictionary;

import org.osgi.framework.hooks.resolver.ResolverHook;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleRevision;

public class FiResolverHook implements ResolverHook {
	
	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	public void filterMatches(BundleRequirement requirement, Collection<BundleCapability> candidates) {
		final String requirementName = requirement.getRevision().getSymbolicName();
				
			Dictionary<String, String> headers = requirement.getRevision().getBundle().getHeaders();
			String importPackagesString = headers.get("Import-Package");
			if (importPackagesString != null && !importPackagesString.isEmpty()) {
			String[] importPackages = importPackagesString.split(",");
			
			for (String importPackage : importPackages) {
				candidates.removeIf(candidate -> {
					Dictionary<String, String> headers2 = candidate.getRevision().getBundle().getHeaders();
					String[] exportPackages = headers2.get("Export-Package").split(",");
					
					for (String exportPackage : exportPackages) {
						if (exportPackage.startsWith(importPackage)) {
							boolean b = !exportPackage.contains(requirementName);
							if (b) {
								System.err.println(requirementName + " darf nicht zugreifen auf das package " + importPackage);
							}
							return b;
						}
					}
					return false;
				});
			}
			}
	}

	@Override
	public void filterResolvable(Collection<BundleRevision> arg0) {
		
	}

	@Override
	public void filterSingletonCollisions(BundleCapability arg0, Collection<BundleCapability> arg1) {
		// TODO Auto-generated method stub
	}
}
