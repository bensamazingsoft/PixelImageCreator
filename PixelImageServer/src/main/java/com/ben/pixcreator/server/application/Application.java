
package com.ben.pixcreator.server.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.ben.pixcreator.server.filters.AppIdFilter;
import com.ben.pixcreator.server.filters.AuthFilter;

public class Application extends ResourceConfig
{

      public Application()
      {

	    register(AppIdFilter.class);
	    register(AuthFilter.class);
      }

}
