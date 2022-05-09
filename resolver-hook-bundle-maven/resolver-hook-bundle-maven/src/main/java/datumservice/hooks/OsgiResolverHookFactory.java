package datumservice.hooks;

import java.util.Collection;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.osgi.framework.hooks.resolver.ResolverHook;
import org.osgi.framework.hooks.resolver.ResolverHookFactory;
import org.osgi.framework.wiring.BundleRevision;

@Component(immediate = true)
@Service(value = ResolverHookFactory.class)
public class OsgiResolverHookFactory implements ResolverHookFactory {

	@Override
	public ResolverHook begin(Collection<BundleRevision> arg0) {
		System.out.println("init resolverHook");
		return new OsgiResolverHook();
	}

}
