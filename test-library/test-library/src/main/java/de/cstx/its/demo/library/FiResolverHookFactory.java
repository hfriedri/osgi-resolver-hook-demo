package de.cstx.its.demo.library;

import java.util.Collection;

import org.osgi.framework.hooks.resolver.ResolverHook;
import org.osgi.framework.hooks.resolver.ResolverHookFactory;
import org.osgi.framework.wiring.BundleRevision;

public class FiResolverHookFactory implements ResolverHookFactory {

	@Override
	public ResolverHook begin(Collection<BundleRevision> arg0) {
		return new FiResolverHook();
	}

}
