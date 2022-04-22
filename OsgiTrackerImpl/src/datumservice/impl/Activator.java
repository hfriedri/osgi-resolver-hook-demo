package datumservice.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import datumservice.DatumService;

public class Activator implements BundleActivator
{
   @Override
   public void start( BundleContext context ) throws Exception
   {
      System.out.println( context.getBundle().getSymbolicName() +  " startet ..." );
      context.registerService( DatumService.class.getName(), new DatumServiceImpl(), null );
      System.out.println( context.getBundle().getSymbolicName() +  " gestartet und Dienst registriert." );
   }

   @Override
   public void stop( BundleContext context ) throws Exception
   {
      System.out.println( context.getBundle().getSymbolicName() +  " gestoppt." );
   }
}
