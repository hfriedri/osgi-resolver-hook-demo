package datumservice.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import datumservice.DatumService;

public class DatumServiceImpl implements DatumService
{
   @Override
   public String getDatum()
   {
      return (new SimpleDateFormat( "yyyy-MM-dd HH:mm" )).format( new Date() );
   }
}
