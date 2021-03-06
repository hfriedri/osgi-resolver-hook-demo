package datumservice.consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import datumservice.DatumService;

public class Activator implements BundleActivator
{
   @SuppressWarnings("rawtypes")
   private ServiceTracker datumServiceTracker;

   @SuppressWarnings({ "unchecked", "rawtypes" })
   @Override
   public void start( final BundleContext context )
   {
	   
      System.out.println( context.getBundle().getSymbolicName() +  " startet ..." );
      datumServiceTracker = new ServiceTracker( context, DatumService.class.getName(),
         new ServiceTrackerCustomizer()
         {
            @Override
            public Object addingService( final ServiceReference reference )
            {
               final DatumService datumService = (DatumService) context.getService( reference );
               if( datumService != null ) {
                  System.out.println( "Konsument-ServiceTracker-2 liest Datum-Service: " + datumService.getDatum() );
               }
                  return datumService;
            }

            @Override
            public void modifiedService( final ServiceReference reference, final Object service ) {/*ok*/}
            @Override
            public void removedService(  final ServiceReference reference, final Object service ) {/*ok*/}
         });

      datumServiceTracker.open();
      System.out.println( context.getBundle().getSymbolicName() +  " gestartet und Tracker geoeffnet." );
   
      context.addBundleListener(new Listener());
   
   }

   @Override
   public void stop( final BundleContext context )
   {
      if( datumServiceTracker != null )
          datumServiceTracker.close();
      System.out.println( context.getBundle().getSymbolicName() +  " gestoppt." );
   }
}