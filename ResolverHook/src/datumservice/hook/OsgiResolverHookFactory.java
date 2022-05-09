package datumservice.hook;

import java.util.Collection;

import org.osgi.framework.hooks.resolver.ResolverHook;
import org.osgi.framework.hooks.resolver.ResolverHookFactory;
import org.osgi.framework.wiring.BundleRevision;

public class OsgiResolverHookFactory implements ResolverHookFactory {

	@Override
	public ResolverHook begin(Collection<BundleRevision> arg0) {
		System.out.println("init resolverHook");
		return new OsgiResolverHook();
	}

}
